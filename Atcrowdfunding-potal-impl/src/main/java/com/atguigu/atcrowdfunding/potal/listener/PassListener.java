package com.atguigu.atcrowdfunding.potal.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

public class PassListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution arg0) throws Exception {
		System.out.println("PassListener");

	}

}
