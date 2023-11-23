using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace CSE445_Homework2_3
{
    class Airline
    {
        int id;
        public encoder_decoder ed = new encoder_decoder();
        public Random r = new Random();

        public Int32 priceCutCount = 0;
        public Int32 PriceOfSeat = 70;

        public static event priceCutEvent priceCut;
        public static event orderProcessedEvent orderProcessed;

        public Int32 numOrdersRejected;
        public Int32 numOrdersCompleted;

        public Airline(int p)
        {
            id = p;
        }
        public void PriceChange(Int32 price)
        {
            Int32 oldPrice = PriceOfSeat;
            PriceOfSeat += price;

            if (priceCut != null)//there is at least a subscriber
            {
                if (oldPrice > PriceOfSeat) //checks if new price is less than original price
                {
                    Console.WriteLine("Airline {0} Tickets are on sale.", id);
                    priceCut(id); // emits event only if the price has been decreased to all 5 agencies

                    priceCutCount++; //increments number of priceCut events
                }
            }
        }

        public Int32 PricingModel()
        {
            Int32 price = r.Next(-10, 15);
            return price;
        }

        public void airlineFunction()
        {
            while (priceCutCount < 10)
            {
                Thread.Sleep(2000);
                Int32 price = PricingModel();//-5
                PriceChange(price);
            }
            if (id == 1)
            {
                Program.airline1Nums[0] = priceCutCount;
                Program.airline1Nums[1] = numOrdersCompleted;
                Program.airline1Nums[2] = numOrdersRejected;
                Program.AirlineThread1Running = false;
            }
            else
            {
                Program.airline2Nums[0] = priceCutCount;
                Program.airline2Nums[1] = numOrdersCompleted;
                Program.airline2Nums[2] = numOrdersRejected;
                Program.AirlineThread2Running = false;
            }
            
        }

        public void runOrder() //event handler
        {
            Thread.Sleep(200);
            string order = Program.mcb.getOneCell(); //retrieves the order from the MultiCellBuffer
            OrderObject oo = ed.decode(order); //decodes the string
            Thread thread = new Thread(() => OrderProcessing(oo, PriceOfSeat));
            thread.Start(); //starts the order processing thread
            thread.Join();
        }

        public void OrderProcessing(OrderObject oo, Int32 unitPrice)
        {
            Thread.Sleep(200);
            if (!isValid(oo.getcardNo()))
            {
                Console.WriteLine("{0} is not a valid credit card number.", oo.getcardNo());
                Console.WriteLine("Agency {0}'s order has been processed at {1}.", oo.senderId, DateTime.Now.ToString("hh:mm:ss"));
                Program.OrdersCompleted++;
                numOrdersCompleted++;
                return;
            }
            else
            {
                Int32 amountToPay = Convert.ToInt32(1.08 * (unitPrice * oo.getAmount())); // unitPrice * amount + tax (8%)  
                orderProcessed(oo.getsenderId(), amountToPay); // emits event to subscribers
                Console.WriteLine("Agency {0}'s order has been processed at {1}.", oo.senderId, DateTime.Now.ToString("hh:mm:ss"));
                Program.OrdersRejected++;
                numOrdersRejected++;
            }
        }
        public bool isValid(int cardNo)
        {
            if (5000 <= cardNo && cardNo <= 7000)
            {
                return true;
            }
            return false;
        }
    }
}
