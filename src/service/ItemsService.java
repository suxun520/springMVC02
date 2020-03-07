package service;

import java.util.List;

import pojo.Items;
import pojo.QueryVo;

public interface ItemsService {
	// 查询商品列表
	public List<Items> getItemsList();

	// 根据id查商品
	public Items getItemById(Integer id);

	// 修改
	public void updateItem(Items item);

	public void test(QueryVo vo);
}
