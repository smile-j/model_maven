package com.dong.base.desgin.structure.adapter;

import com.dong.base.desgin.entity.Orange;
import com.dong.base.desgin.entity.bag.AppleBag;
import com.dong.base.desgin.entity.bag.OrangeBag;

public class AdapterClient {
	public static void main(String[] args){
		Orange orange = new Orange("peter",100);
//		OrangeBag bag = new OrangeBag();
		OrangeBag bag = getBag2();
		orange.pack(bag);
	}

	/**
	 * 取桔子包装
	 * @return
     */
//	private static OrangeBag getBag(){
//		OrangeBag bag = new OrangeBag();
//		return bag;
//	}

	private static OrangeBag getBag2(){
		//准备用途苹果盒代替
		AppleBag appleBag = new AppleBag();

		//把苹果盒适配成桔子包装盒
		OrangeBag orangeBag = new OrangeBagAdapter(appleBag);

		return orangeBag;
	}
}
