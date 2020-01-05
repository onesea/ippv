package phone

var (
)

type Node struct {
	flags int32
	iso string
	child [10]*Node
}

type Parser struct {
	tree Node
}

func (this *Parser) Add(cc, iso string) {
	root := &this.tree
	for i := 0; i < len(cc); i+=1 {
		index := cc[i] - '0'
		if root.child[index] == nil {
			root.child[index] = &Node{}
		}
		node := root.child[index]
		if i == len(cc) - 1 {
			node.flags = 1
			node.iso = iso
		}
		root = node
	}
}

func (this *Parser) Parse(phone string) (ln,ld,ac uint, iso string) {
	root := &this.tree
	ln = 0
	ld = 0
	ac = 0
	iso = ""
	for i := 0; i < len(phone); i+=1 {
		index := phone[i] - '0'
		node := root.child[index]
		if node == nil {
			break
		}
		if node.flags == 1 {
			if ln == 0 {
				ln = uint(i + 1)
			} else if ld == 0 {
				ld = uint(i + 1)
			} else {
				ac = uint(i + 1)
			}
			iso = node.iso
		}
		root = node
	}
	return
}

type LibPhone Parser

