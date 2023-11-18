#include <linux/kernel.h>
#include <sys/syscall.h>
#include <stdio.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    long didItWork = syscall(449);

    if(didItWork < 0)
    {
        printf("%ld: Nope\n", didItWork);
    }
    else
    {
        printf("%ld: Yep\n", didItWork);
    }

    return 0;
}