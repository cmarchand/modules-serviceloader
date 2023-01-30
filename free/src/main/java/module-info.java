module top.marchand.demo.java17.modules.free {
  exports top.marchand.demo.java17.modules.free to top.marchand.demo.java17.product;
  requires top.marchand.demo.java17.product.contract;
  provides top.marchand.demo.java17.modules.contract.Service with top.marchand.demo.java17.modules.free.ServiceImpl;
  opens top.marchand.demo.java17.modules.free to top.marchand.demo.java17.product;
}