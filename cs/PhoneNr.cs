using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ippv
{
    class Node
    {
        public int type_;
        public string iso_;
        public Node[] children_;
    }
    class PhoneNr
    {
        public static Node[] tree_;

        public static void init()
        {
            tree_ = new Node[10];
        }

        public static void add(string cc, string iso)
        {
            var tree = tree_;
            for (int i = 0; i < cc.Length; ++i)
            {
                var digit = cc[i] - '0';
                var node = tree[digit];
                if (node == null)
                {
                    node = new Node();
                    tree[digit] = node;
                }
                if (i == cc.Length - 1)
                {
                    node.type_ = 1;
                    node.iso_ = iso;
                    break;
                }
                if (node.children_ != null)
                {
                    tree = node.children_;
                    continue;
                }
                node.children_ = new Node[10];
                tree = node.children_;
            }
        }

        public static int match(string no /*phone no*/, ref int ld /*leading digits*/, ref int ac /*area code*/, ref string iso /*country iso code*/)
        {
            int len = 0;
            var tree = tree_;
            for (var i = 0; i < no.Length && tree != null; ++i)
            {
                var digit = no[i] - '0';
                var node = tree[digit];
                if (node == null)
                    break;

                if (node.type_ == 1 || node.children_ == null)
                {
                    if (len == 0) len = i + 1;
                    else if (ld == 0) ld = i + 1;
                    else ac = i + 1;
                    iso = node.iso_;
                }

                tree = node.children_;
            }
            return len;
        }

    }
}
