//extern crate phone;
use phone::{IPPv};

fn main() {
    // init parse tree
    "80".add_cc("I80");
    "801".add_cc("I801");
    "8012".add_cc("I8012");

    // test
    let res = "8099999999".ippv();
    println!("{:?}", res);
    let res = "80199999999".ippv();
    println!("{:?}", res);
    let res = "801299999999".ippv();
    println!("{:?}", res);
}
