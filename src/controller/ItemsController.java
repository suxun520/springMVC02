package controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import exception.CustomException;
import pojo.Items;
import pojo.QueryVo;
import service.ItemsService;

@Controller
public class ItemsController {
	@Autowired
	private ItemsService itemsService;

	@RequestMapping("/items.action")
	// model负责传输数据，retuen到达页面，符合解耦思想
	public String itemList(Model model) throws CustomException {
		// 未知异常
		// Integer i = 1 / 0;

		List<Items> itemList = itemsService.getItemsList();
		// 预期异常 假设itemList为空

		// if (null == null) { throw new CustomException("没有商品！！"); }

		model.addAttribute("itemList", itemList);
		return "itemList";
	}

	@RequestMapping("/itemEdit.action")
	// public ModelAndView itemEdit(HttpServletRequest request) {
	public ModelAndView itemEdit(Integer id) {
		// String id = request.getParameter("id");
		ModelAndView mv = new ModelAndView();
		// Items item = itemsService.getItemById(Integer.parseInt(id));
		Items item = itemsService.getItemById(id);
		mv.addObject("item", item);
		mv.setViewName("editItem");
		return mv;
	}

	// // 提交修改页面，入参是poji类型
	// @RequestMapping("/updateitem.action")
	// public ModelAndView updateItem(Items item) {
	// itemsService.updateItem(item);
	// ModelAndView mv = new ModelAndView();
	// mv.setViewName("success");
	// return mv;
	// }

	// 提交修改页面，入参是queryvo类型(对象包对象)
	@RequestMapping("/updateitem.action")
	public String updateItem(QueryVo vo, MultipartFile pictureFile) throws Exception {
		// 生成文件名(不含后缀)
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		// 获取文件格式
		String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		// 修改后的文件名
		String newName = name + "." + ext;
		// 将文件上传至某个位置
		pictureFile.transferTo(new File("D:\\upload\\" + newName));

		vo.getItems().setPic(newName);
		itemsService.updateItem(vo.getItems());
		// 重定向
		return "redirect:/itemEdit.action?id=" + vo.getItems().getId();
		// 转发
//		return "forward:/items.action";
	}

	// 删除多个
	@RequestMapping(value = "/deletes.action")
	public ModelAndView deletes(Integer[] ids) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("success");
		return null;
	}

	// 更新多个
	@RequestMapping(value = "/updates.action")
	public ModelAndView updates(QueryVo vo) {

		ModelAndView mv = new ModelAndView();
		itemsService.test(vo);

		/*
		 * mv.setViewName("success"); return mv;
		 */
		return null;
	}

	// json数据交互
	@RequestMapping(value = "/json.action")
	@ResponseBody
	// springMVC解析的是json格式的字符串 注意！！！ 前台传来的是字符串才能解析成对象
	public Items json(Items items) {

		return items;
	}

	@RequestMapping(value = "/login.action", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(HttpSession session, String username) {
		session.setAttribute("username", username);
		return "redirect:/items.action";
	}
}
