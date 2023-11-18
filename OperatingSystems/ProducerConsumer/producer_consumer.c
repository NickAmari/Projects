#include <linux/module.h>
#include <linux/init.h>
#include <linux/kthread.h>
#include <linux/sched.h>
#include <linux/sched/signal.h>
#include <linux/semaphore.h>
#include <linux/timekeeping.h>
#include <linux/slab.h>

static int producer_function(void *args);
static int consumer_function(void *args);

/* Kernel Module Parameters*/
static int uid = 0, buff_size = 0, p = 0, c = 0;

module_param(uid, int, 0664); // is the octal code for read and write privileges
module_param(buff_size, int, 0664);
module_param(p, int, 0664);
module_param(c, int, 0664);

/* Parameter Meta Data */
MODULE_PARM_DESC(uid, "User Identification");
MODULE_PARM_DESC(buff_size, "Size of shared buffer a positive number");
MODULE_PARM_DESC(p, "Number of producers 0 or 1");
MODULE_PARM_DESC(c, "Number of consumers a positive number");

//How to call Params : sudo insmod producer_consumer.ko UID=X buffSize=X nP=X nC=x

/* Semaphore creation*/
struct semaphore mutex;
struct semaphore empty;
struct semaphore full;

/* global vars*/
static struct task_struct *pthread_1; // not sure if this is necessary need to fix the __init function
static struct task_struct *cthread_1;
static int count = 0; // for consumers to grab from the buffer
static u64 totalElapsedTime;
static int process_counter = 0;
struct task_struct** buffer;
static int pflag = 0;
static int cflag = 0;

/*Module Init*/
static int pc_init(void) {
	// params are passed with the module_param funcs
	buffer = kmalloc(buff_size * sizeof(struct task_struct*), GFP_KERNEL);
	//initializing semaphores to their default values
	sema_init(&mutex, 1);
	sema_init(&empty, buff_size);
	sema_init(&full, 0);
	//create producer thread
	if (p == 1) { // it should be one
		pthread_1 = kthread_run(producer_function,NULL, "producer");
		//pthread_1 = kthread_run(producer_function,NULL, "producer");
	}
	else { // if its 0 or anything else we just exit
		return -1;
	}
	
	//lets just do 1 consumer for now
	if (c == 1) {
		cflag = 1;
		cthread_1 = kthread_run(consumer_function,NULL, "consumer");
	}

	return 0;
}
/* Thread function definitions */

static int producer_function(void *args) {
	struct task_struct* proc;
	int pUID = 0;
	// V looks through each process currently running one at a time
	for_each_process(proc) {
		if (kthread_should_stop()) {
			do_exit(0);
		}
		// V process's have a user Id of who started them contained in their task_Struct
		pUID = proc->cred->uid.val;
		if (pUID == uid) { //if the process belongs to given user, UID is a global param passed
			++process_counter;
			
			if (down_interruptible(&empty)) {
				break;
			}

			if (down_interruptible(&mutex)) { // down_interruptible basically aquires a lock so now only this thread can move forward once its false
				break;
			}	
			//critical section : at this point only one thread should be running this code at a time
			buffer[count] = proc; //add current task pointer to buffer
			count = (count +1)%buff_size;
			//end critical seciton : up() will release the lock and allow a diff thread to access the buffer
			up(&mutex);
			up(&full);
		}
	}
	pflag = 1;
	return 1;
}
					

static int consumer_function(void *arg) {
	struct task_struct* proc;
	u64 start_time, elapsed;
	printk("Consumer thread made and running\n");
	
	while (!kthread_should_stop()) {
		
		if (down_interruptible(&full)) { // down_interruptible basically aquires a lock so now only this thread can move forward once its false
			break;
		}
				
		if (down_interruptible(&mutex)) {
			break;
		}
		
		//CS
		proc = buffer[count];

		//calc elapsed time
		start_time = ktime_get_ns();
		elapsed = start_time - proc->start_time;
		totalElapsedTime += elapsed;

		//print elapsed time
		printk("%s Consumed Item# %d on buffer index: %d PID: %d Elapsed Time- %llu", current->comm, process_counter, count, proc->cred->uid.val, elapsed);
		/*[<Consumer-thread-name>] Consumed Item#-<Item-Num> on buffer index: <buffer-index> PID:<PID consumed>  
		Elapsed Time- <Elapsed time of the consumed PID in HH:MM:SS>*/

		up(&mutex);
		up(&empty);
		/*time calcs ?*/
	}
	printk("consumer thread finished\n");
	return 1;
}
	

/*Module Exit*/
static void pc_exit(void) {
	up(&mutex);
	up(&empty);
	up(&full);
	if (pflag == 0) {//if the producer has not finished yet, stop it
		kthread_stop(pthread_1);
	}

	if (cflag == 1) {// 1 means there is a consumer thread to delete, if not skip
		kthread_stop(cthread_1);
	}
	
	printk("The total elapsed time of all processes for UID %d is %llu", uid, totalElapsedTime);
}

module_init(pc_init);
module_exit(pc_exit);
MODULE_LICENSE("GPL");
