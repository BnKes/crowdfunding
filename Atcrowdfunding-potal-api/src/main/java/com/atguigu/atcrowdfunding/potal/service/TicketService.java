package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.Ticket;

public interface TicketService {

	Ticket getTicketByMemberId(Integer memberid);

	void saveTicket(Ticket ticket);

	void updatePstep(Ticket ticket);

}