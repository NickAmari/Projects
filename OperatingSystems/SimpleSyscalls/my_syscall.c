#include <linux/kernel.h>
#include <linux/syscalls.h>

SYSCALL_DEFINE0(my_syscall)
{
    printk("This is the new system call\n");
    return 0;
}
