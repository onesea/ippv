const MAX:usize = 10;
struct Node {
    flags: u64,
    iso: String,
    child: [Option<Box<Node>>; MAX]
}

static mut TREE: [Option<Box<Node>>; MAX] = [None,None,None,None,None,None,None,None,None,None];

pub fn add(nr: &str, iso: &str) {
    let mut tree = unsafe { &mut TREE };
    for (i, ch) in nr.chars().enumerate() {
        let index = ch.to_digit(10).unwrap() as usize;
        let value = if i == nr.len() - 1 {1} else {0};
        if tree[index].as_mut().is_none() {
            let node = tree[index].get_or_insert( Box::<Node>::new(
                Node {
                    flags: value,
                    iso: if value != 0 {iso.to_string()} else {"".to_owned()},
                    child: Default::default()
                }));
            tree = &mut node.child;
        } else {
            let node = tree[index].as_mut().unwrap().as_mut();
            if value != 0 {
                node.flags = value;
                node.iso = iso.to_string();
            }
            tree = &mut node.child;
        }
    }
 }

pub fn parse(nr: &str) -> (u8, u8, u8, &str) {
    let mut len = 0u8;
    let mut ld = 0u8;
    let mut ac = 0u8;
    let mut iso: &str = "";

    let mut tree = unsafe { &TREE };
    for (i, ch) in nr.chars().enumerate() {
        let index = ch.to_digit(10).unwrap() as usize;
        let opt = tree[index].as_ref();
        if opt.is_none() { break; }
        let node = opt.unwrap().as_ref();
        if node.flags == 1 {
            if len == 0 { len = i as u8 + 1; }
            else if ld == 0 { ld = i as u8 + 1; }
            else { ac = i as u8 + 1; }
            iso = &node.iso;
        }
        tree = &node.child;
    }

    return (len,ld,ac,iso);
} 
