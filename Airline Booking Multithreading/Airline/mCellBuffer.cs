using System;
using System.Threading;

namespace CSE445_Homework2_3
{
    class mCellBuffer
    {
        const int SIZE = 3;
        string[] data = new string[SIZE];
        int head = 0, tail = 0, n = 0;
        private Object BufferLock = new Object();

        public void setOneCell(string c)
        {

            lock (BufferLock)
            {
                while (n == SIZE)
                {
                    Monitor.Wait(BufferLock);
                }

                data[tail] = c;
                tail = (tail + 1) % SIZE;
                n++;

                Monitor.Pulse(BufferLock);

            }
        }

        public string getOneCell()
        {

            lock (BufferLock)
            {

                while (n == 0)
                {
                    Monitor.Wait(BufferLock);
                }

                string c = data[head];
                head = (head + 1) % SIZE;
                n--;

                Monitor.Pulse(BufferLock);

                return c;
            }
        }
    }
}
