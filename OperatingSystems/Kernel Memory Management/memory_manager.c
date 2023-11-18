#include <linux/kernel.h>
#include <linux/hrtimer.h>
#include <linux/ktime.h>
#include <linux/mm.h>
#include <linux/sched/mm.h>
#include <linux/module.h>

static int pid = 0;

module_param(pid, int, 0664); // is the octal code for read and write privileges

/*Global Vars*/
static int rssCount = 0;
static int swapCount = 0;
static int wssCount = 0;
unsigned long timer_interval_ns = 10e9; // 10-second timer
static struct hrtimer hr_timer;
static int pUID;

static int walk_page_table(struct mm_struct * mm,unsigned long address) {
	pgd_t *pgd;
	p4d_t *p4d; 
	pmd_t *pmd;
	pud_t *pud;
	pte_t *ptep, pte;
    	

	pgd = pgd_offset(mm, address);                    // get pgd from mm and the page address
	if (pgd_none(*pgd) || pgd_bad(*pgd)) {           // check if pgd is bad or does not exist
        return 0;
	}

	p4d = p4d_offset(pgd, address);                   // get p4d from from pgd and the page address
	if (p4d_none(*p4d) || p4d_bad(*p4d)){          // check if p4d is bad or does not exist
        return 0;
	}

	pud = pud_offset(p4d, address);                   // get pud from from p4d and the page address
	if (pud_none(*pud) || pud_bad(*pud)){          // check if pud is bad or does not exist
        return 0;
	}

	pmd = pmd_offset(pud, address);               // get pmd from from pud and the page address
	if (pmd_none(*pmd) || pmd_bad(*pmd)){       // check if pmd is bad or does not exist
        return 0;
	} 

	ptep = pte_offset_map(pmd, address);      // get pte from pmd and the page address
	if (!ptep){return 0;}                                         // check if pte does not exist
	pte = *ptep;
	
	if ( pte_present(pte) == 1) {
		rssCount++;
		if (pte_young(*ptep)) {
			wssCount++;
			test_and_clear_bit(_PAGE_BIT_ACCESSED, &ptep->pte);//returns 1 or 0
		}
        }
        else { 
	    swapCount++;
        }

    return 1;
}

enum hrtimer_restart timer_callback( struct hrtimer *timer_for_restart )
{
      struct vm_area_struct * vmaList;
      struct task_struct* proc;
      int rssRet, swapRet, wssRet;
    // Resetting the timer, which also means… ?
      ktime_t currtime , interval;
      currtime  = ktime_get();
      interval = ktime_set(0,timer_interval_ns); 
      hrtimer_forward(timer_for_restart, currtime , interval);
      
    // Do the measurement, like looking into VMA and walking through memory pages
	//GATHERING PROCESS
	for_each_process(proc) {
		pUID = proc->pid;
		if (pUID == pid) {
			//do everything that needs to be done
			vmaList = proc->mm->mmap;//list of VMAs I'm not sure if this is a corect definition
			//must iterate through every VMA and check each page
			while (vmaList != NULL) { // for each VMA
				unsigned long i;
				for(i = vmaList->vm_start; i < vmaList->vm_end + 1; i += PAGE_SIZE) {
					walk_page_table(proc->mm, i);
				}
				vmaList = vmaList->vm_next;
			}
		//NOW SHOULD HAVE COUNT OF SWAPS AND PHYSICAL MEMORY RSS
		rssRet = rssCount * 4;
		swapRet = swapCount * 4;
		wssRet = wssCount * 4;
		
		printk("PID [%d]: RSS=%d KB, SWAP=%d KB, WSS=%d KB", pid, rssRet, swapRet, wssRet);
		rssCount = 0;
		swapCount = 0;
		wssCount = 0;
		}
	}
	
	// And also do the Kernel log printing aka printk per requirements
    return HRTIMER_RESTART;
}

static int __init timer_init(void) {
    ktime_t ktime ;
	ktime = ktime_set( 0, timer_interval_ns );
    hrtimer_init( &hr_timer, CLOCK_MONOTONIC, HRTIMER_MODE_REL );
    hr_timer.function = &timer_callback;
    hrtimer_start( &hr_timer, ktime, HRTIMER_MODE_REL );
    return 0;
}

static void __exit timer_exit(void) {
    int ret;
    ret = hrtimer_cancel( &hr_timer );
    if (ret) printk("The timer was still in use...\n");
    printk("HR Timer module uninstalling\n");
    
}

module_init(timer_init);
module_exit(timer_exit);
MODULE_LICENSE("GPL");
