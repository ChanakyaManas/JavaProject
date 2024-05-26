package com.orm.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orm.Dto.OrderDTO;
import com.orm.Entity.OrderPojo;
import com.orm.Repo.OrderRepo;
import com.orm.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepo or;
	
	@Autowired
	private ModelMapper mapper; //model mapper is used 
	
	@Override
	public ResponseEntity<OrderDTO> saveOrder(OrderDTO op) {
		logger.info("I am in orderserviceimpl saveorder method started"+op);
					
		// # Level 1 
		
		/*
		 * OrderPojo k = new OrderPojo(); k.setOrderid(op.getOrderid());
		 * k.setOrderempName(op.getOrderempName()); k.setOrderdate(op.getOrderdate());
		 * k.setOrderdeliverydate(op.getOrderdeliverydate());
		 * k.setOrderdeptId(op.getOrderdeptId());
		 * k.setOrderAppManager(op.getOrderAppManager()); k.setOpp(op.getOpp());
		 */
		
		// # Level 2
		
		//ModelMapper m = new ModelMapper();
		//OrderPojo k = m.map(op, OrderPojo.class);
		
		// # Level 3			//source, destination type	
		OrderPojo k = mapper.map(op, OrderPojo.class);
		
		or.save(k);
		
		logger.info("I am in orderserviceimpl saveorder method completed"+op);
		
		return new ResponseEntity<OrderDTO>(op, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<OrderDTO> updateOrder(OrderDTO op) {
		logger.info("I am in orderserviceimpl updateorder method started : "+op);
		
		OrderPojo k = mapper.map(op, OrderPojo.class);
		
		or.save(k);

		logger.info("I am in orderserviceimpl updateorder method completed : "+op);
		
		return new ResponseEntity<OrderDTO>(op, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> deleteOrder(Integer oid) {
		logger.info("I am in orderserviceimpl deleteorder method started");
		or.deleteById(oid);
		logger.info("I am in orderserviceimpl deleteorder method completed");
		return new ResponseEntity<Object>(oid+" : deleted successfully.", HttpStatus.GONE);
	}

	@Override
	public OrderDTO getByOrderId(Integer oid) {
		logger.info("I am in orderserviceimpl getbyorderid method started");
		Optional<OrderPojo> k = or.findById(oid);
		OrderPojo m = k.get();
		
		// # Level 1
		//OrderDTO od = new OrderDTO();
		//od.setOrderid(m.getOrderid()); od.setOrderempName(m.getOrderempName()); od.setOrderdate(m.getOrderdate());
		//od.setOrderdeliverydate(m.getOrderdeliverydate()); od.setOrderdeptId(m.getOrderdeptId());
		//od.setOrderAppManager(m.getOrderAppManager()); od.setOpp(m.getOpp());
		
		// # Level 2
		//ModelMapper mm = new ModelMapper();
		//OrderDTO od = mm.map(m, OrderDTO.class);
		
		// # Level 3
		OrderDTO od = mapper.map(m, OrderDTO.class);
		logger.info(od.toString());
		
		logger.info("I am in orderserviceimpl getbyorderid method completed");
		return od;
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		logger.info("I am in orderserviceimpl getall method started");
		
		//List<OrderDTO> l = new ArrayList<>();
		
		List<OrderPojo> k = or.findAll();
		
		// # Level 2
		OrderDTO[] d = mapper.map(k, OrderDTO[].class);
		List<OrderDTO> l = Arrays.asList(d);
		for(OrderDTO q : l) {
			logger.info(q.toString());
		}
		// # Level 1
		//for(OrderPojo op : k) {
			//OrderDTO d = mapper.map(op, OrderDTO.class);
			//l.add(d);
		//}
		
		logger.info("I am in orderserviceimpl getall method completed");
		return l;
	}
}
