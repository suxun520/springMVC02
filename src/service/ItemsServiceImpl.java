package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ItemsDao;
import pojo.Items;
import pojo.QueryVo;

@Service
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	private ItemsDao itemsDao;

	// 查询商品列表
	public List<Items> getItemsList() {
		return itemsDao.selectByExampleWithBLOBs(null);
	}

	// 根据id查商品
	public Items getItemById(Integer id) {
		return itemsDao.selectByPrimaryKey(id);
	}

	// 修改
	public void updateItem(Items item) {
		itemsDao.updateByPrimaryKeyWithBLOBs(item);

	}

	@Override
	public void test(QueryVo vo) {
		System.err.println(vo.getItemsList().get(1).getName());
		System.out.println(vo.getItemsList().get(0).getName());
	}
}
