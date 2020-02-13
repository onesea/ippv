extern crate phone;

fn main() {
    phone::add("80", "I80");
    phone::add("801", "I801");
    phone::add("8012", "I8012");
    let (len,ld,ac,iso) = phone::parse("8099999999");
    println!("{} {} {} {}", len, ld, ac, iso);
    let (len,ld,ac,iso) = phone::parse("80199999999");
    println!("{} {} {} {}", len, ld, ac, iso);
    let (len,ld,ac,iso) = phone::parse("801299999999");
    println!("{} {} {} {}", len, ld, ac, iso);
}
