#include <linux/module.h>
#include <linux/kernel.h>

void my_name(void)
{
    printk("Nicholas Amari\n");
}
int hello_init(void)
{
    //printk("Hello World!\n");
    my_name();
    return 0;
}
void hello_exit(void)
{
    //printk("Goodbye World!\n");
}

module_init(hello_init);
module_exit(hello_exit);
MODULE_LICENSE("GPL");
