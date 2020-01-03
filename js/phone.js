class Base {
    constructor() {}
}

class PhoneNo extends Base {
  constructor(level) {
    super()
    if (level) {
      this.level_ = level
    }
    this.tree_ = []
  }

  add(cc,iso) {
      let tree = this.tree_
      for (let i = 0; i < cc.length; ++i) {
          const index = cc[i] - '0'
          if (! tree[index]) {
              tree[index] = {
                  flag: 0
              }
          }
          const type = (i==cc.length-1)?1:0
          if (type != 0) {
              tree[index].iso = iso
              tree[index].flag = type
          } else {
              if (!tree[index].child) {
                  tree[index].child = []
              }
              tree = tree[index].child
          }

      }
  }

    parse(phone) {
        let res = {}
        let tree = this.tree_
        for (let i = 0; i < phone.length; ++i) {
            const index = phone[i] - '0'
            if (! tree[index]) {
                break
            }
            if (tree[index].flag != 0) {
                if (!res.len) res.len = i + 1
                else if (!res.ld) res.ld = i + 1
                else res.ac = i + 1
                res.iso = tree[index].iso
            }
            if (!tree[index].child) {
                break
            }
            tree = tree[index].child
        }
        return res
    }
}

module.exports = PhoneNo