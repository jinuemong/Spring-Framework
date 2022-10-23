package skuniv.cs.Hello;
public class OrderUpdateService {
	private OrderDao orderDao;
	
	public OrderUpdateService(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
	public Long order_update(OrderRegisterRequest oreq) 
			throws Exception{
		Order order = orderDao.selectByOdBnumber(oreq.getOdbnumber());
		if(order==null) {
			throw new Exception("DuplicateMemberException");
		}
		Order newOrder = new Order(oreq.getOdbook(),oreq.getOdemail(),
				oreq.getBookemail(),oreq.getOdbnumber() 
				, order.getRegisterDateTime());
		orderDao.update(newOrder);
		return newOrder.getOid();
	}
}
