# aw10-final


Please develop a **fully functional** online purchase order system.

- It should have a superb collection of goods merchandises
- Customer can browse/search for merchandises, add selected one into his shopping cart and checkout to complete a transaction.
- User can get delivery status updates continuously.

The system should be of a **reactive architecture**, which means it should be 

-  Responsive: it should response to the user request timely.
-  Resilient: it should not be easily broken down.
-  Elastic: it should be flexible to scale out.
-  Message Driven: it should has loosely coupled components that communicates with each other asynchronously.


Please design tests/experiements to demostrate that your system fulfills such requirements as stated in [The Reactive Manifesto](https://www.reactivemanifesto.org)

**Submit your codes/documents/tests/experiements of your system.**



#### 功能模块说明

##### 1、pos-discovery

###### 端口号：8761

discovery中心，负责为每个微服务提供一个节点

##### 2、pos-gateway

###### 端口号：8084

暴露给外界的网关，外界的请求通过网关转发给对应的rest微服务节点

##### 3、pos-products

###### 端口号：8082

产品管理模块，实现的api：

```java
/products
	get:	所有产品信息
/products/{productId} 	
	get:	根据id来获取某一特定产品信息
```

##### 4、pos-carts

###### 端口号：8081

购物车模块，实现的api：

```
/carts
	get:		所有购物车信息		
	post:		创建一个新的购物车 request body : cartDto
/carts/{cartId}
	get:		根据cartId获取特定的购物车信息
	post:		向购物车中添加产品 request body : cartItemDto
/carts/{cartId}/total
	get:		计算该购物车中产品的总价
/carts/{cartId}/checkout
	post:		结账
```

其中结账功能需要发送post请求到pos-order模块以新建一个订单order

##### 5、pos-counter

###### 端口号：8083

计算模块，pos-cart模块调用该模块的service来获取特定购物车中的总价

##### 6、pos-order

###### 端口号：8085

订单模块，实现的api：

```
/orders
	get:		获取所有订单信息
	post:		新建一个订单 request body : cartDto 
/order/{orderId}
	get:	根据orderId获取特定订单信息
```

其中，创建订单服务同时会发送post请求到pos-delivery模块以新建一个物流信息

##### 7、pos-delivery

###### 端口号：8086

物流模块，实现的api：

```
/delivery
	get:		获取所有物流信息
	post:		新建一个物流 request body : orderDto 
/delivery/{deliveryId}
	get:	根据deliveryId获取特定物流信息
```

