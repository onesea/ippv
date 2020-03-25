pub trait IPPv {
	fn add_cc(&self, iso: &str);
	fn ippv(&self) -> (u8, u8, u8, &str);
}

impl IPPv for str {
	fn add_cc(&self, iso: &str) { add(self, iso) }
	fn ippv(&self) -> (u8, u8, u8, &str) { parse(self) }
}

const MAX_CHILD:usize = 10;
struct Node {
    flags: u64,
    iso: String,
    child: [Option<Box<Node>>; MAX_CHILD]
}

static mut TREE: [Option<Box<Node>>; MAX_CHILD] = [None,None,None,None,None,None,None,None,None,None];

fn add(nr: &str, iso: &str) {
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

fn parse(nr: &str) -> (u8, u8, u8, &str) {
    let mut res = (0u8,0u8,0u8,"");
    let mut tree = unsafe { &TREE };

    for (i, ch) in nr.chars().enumerate() {
        let index = ch.to_digit(10).unwrap() as usize;
        let opt = tree[index].as_ref();
        if opt.is_none() { break; }
        let node = opt.unwrap().as_ref();
        if node.flags == 1 {
            let len = (i + 1 ) as u8;
            if res.0 == 0 { res.0 = len; }
            else if res.1 == 0 { res.1 = len; }
            else { res.2 = len; }
            res.3 = &node.iso;
        }
        tree = &node.child;
    }

    res
} 
