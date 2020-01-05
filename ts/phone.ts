class Node {
    public type: Number
    public iso: string
    public child: Node[]
};

export class PhoneNo {
    private tree: Node
    constructor() {
        this.tree = new Node()
    }

    public add(cc: string, iso: string) {
        let root = this.tree
        for (let i = 0; i < cc.length; ++i) {
            let index = cc.charCodeAt(i) - '0'.charCodeAt(0)
            if (!root.child || ! root.child[index]) {
                if (!root.child) root.child = []
                root.child[index] = new Node()
            }
            let node = root.child[index]
            if (i == cc.length - 1) {
                node.type = 1
                node.iso = iso
            } else {
                root = node
            }
        }
    }

    public parse(phone: string): [number,number,number,string] {
        let ln = 0
        let ld = 0
        let ac = 0
        let iso:string = null
        let root = this.tree
        for (let i = 0; i < phone.length; ++i) {
            let index = phone.charCodeAt(i) - '0'.charCodeAt(0)
            if (!root.child || ! root.child[index]) {
                break
            }
            let node = root.child[index]
            if (node.type == 1) {
                if (ln == 0) ln = i + 1
                else if (ld == 0) ld = i + 1
                else ac = i + 1
                iso = node.iso
            }
            root = node
        }
        return [ln,ld,ac,iso]
    }
};
