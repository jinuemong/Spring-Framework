package skuniv.cs.Hello;

import java.time.LocalDateTime;

public class OrderRegisterService {
	private OrderDao orderDao;

	public OrderRegisterService(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Long order_regist(OrderRegisterRequest oreq) throws Exception {
		Order order = orderDao.selectByOdBnumber(oreq.getOdbnumber());
		if (order != null) {
			throw new Exception("DuplicateMemberException");
		}
		Order newOrder = new Order(oreq.getOdbook(), oreq.getOdemail(), oreq.getBookemail(),
				oreq.getOdbnumber() ,LocalDateTime.now() );
		orderDao.insert(newOrder);
		return newOrder.getOid();

	}
}
