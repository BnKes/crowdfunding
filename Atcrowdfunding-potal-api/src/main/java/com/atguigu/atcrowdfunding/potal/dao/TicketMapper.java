package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Ticket;

public interface TicketMapper {

	Ticket getTicketByMemberId(Integer memberId);

	void saveTicket(Ticket ticket);

	void updatePstep(Ticket ticket);

}
