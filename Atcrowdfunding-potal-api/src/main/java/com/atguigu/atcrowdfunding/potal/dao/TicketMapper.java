package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Ticket;

public interface TicketMapper {

	Ticket getTicketByMemberId(Integer memberId);

	void saveTicket(Ticket ticket);

	void updatePstep(Ticket ticket);

	void updatePiidAndPstep(Ticket ticket);

	Member getMemberByPiid(String piid);

	void updateStatus(Member member);

}
