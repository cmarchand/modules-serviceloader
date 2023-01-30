module top.marchand.demo.java17.product {
  exports top.marchand.demo.java17.modules;
  requires top.marchand.demo.java17.product.contract;
  uses top.marchand.demo.java17.modules.contract.Service;
}