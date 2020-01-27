
class _Node {
  int value = 0;
  String iso;
  List<_Node> child = List(10);
}

var _TREE = _Node();

int add(String cc, String iso) {
  var tree = _TREE;
  for (var i = 0; i < cc.length; ++i) {
    var d = cc.codeUnitAt(i) - '0'.codeUnitAt(0);
    if (tree.child[d] == null) {
      tree.child[d] = _Node();
    }
    var node = tree.child[d];
    if (i == cc.length - 1) {
      node.value = 1;
      node.iso = iso;
    }
    tree = node;
  }
  return 0;
}

class PhoneInfo {
  int cc = 0;
  int ld = 0;
  int ac = 0;
  String iso;
}

PhoneInfo match(String phone) {
  var tree = _TREE;
  var info = PhoneInfo();
  for (var i = 0; i < phone.length && tree != null; ++i) {
    var d = phone.codeUnitAt(i) - '0'.codeUnitAt(0);
    var node = tree.child[d];
    if (node != null && node.value == 1) {
      if (info.cc == 0) {
        info.cc = i + 1;
      } else if (info.ld == 0) {
        info.ld = i + 1;
      } else {
        info.ac = i + 1;
      }
      info.iso = node.iso;
    } 
    tree = node;
  }
  return info;
}

int calculate() {
  return 6 * 7;
}