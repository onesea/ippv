//extern crate phone;
use phone::{IPPv};

fn init() {
    for t in &[("80","i80"), ("801","i801"),("8012","i8012")] {
        t.0.add_cc(t.1);
    }
}

fn main() {
    // init parse tree
    init();

    // test
    for nr in &["8099999999", "80199999999","801299999999"] {
        let res = nr.ippv();
        println!("{}\t{:?}", nr, res);
    }
}
