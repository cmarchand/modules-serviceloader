import top.marchand.demo.java17.modules.professional.ServiceImpl;

module top.marchand.demo.java17.modules.free {
  exports top.marchand.demo.java17.modules.professional;
  requires top.marchand.demo.java17.product.contract;
  provides top.marchand.demo.java17.modules.contract.Service with ServiceImpl;
  opens top.marchand.demo.java17.modules.professional to top.marchand.demo.java17.product;
}