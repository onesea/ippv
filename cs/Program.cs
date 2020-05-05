using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ippv
{
    class Program
    {
        static void init()
        {
            PhoneNr.init();
            var cc_arr = new string[,] { { "80", "C80" }, { "801", "C801" }, { "8011", "C8011" }, { "82", "C82" }, { "821", "C821" }, { "8211", "C8211" } };
            for (int i = 0; i < cc_arr.Length / 2; i++)
            {
                PhoneNr.add(cc_arr[i, 0], cc_arr[i, 1]);
            }
        }
        static void Main(string[] args)
        {
            init();

            var phone_arr = new string[] { "8095555", "80195555", "801195555" , "821195555" };
            var query = from item in phone_arr where ! string.IsNullOrEmpty(item) select item;

            foreach(var phone in query)
            {
                int cc = 0, ld = 0, ac = 0;
                string iso = null;
                cc = PhoneNr.match(phone, ref ld, ref ac, ref iso);
                Console.WriteLine(phone + " -> " + cc + "," + ld + "," + ac + "," + iso);
            }
        }
    }
}
