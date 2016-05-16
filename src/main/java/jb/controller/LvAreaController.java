package jb.controller;

import com.alibaba.fastjson.JSON;
import jb.pageModel.*;
import jb.service.LvAreaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

/**
 * LvArea管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvAreaController")
public class LvAreaController extends BaseController {

	@Autowired
	private LvAreaServiceI lvAreaService;


	/**
	 * 跳转到LvArea管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvarea/lvArea";
	}

	/**
	 * 获取LvArea数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvArea lvArea, PageHelper ph) {
		return lvAreaService.dataGrid(lvArea, ph);
	}
	/**
	 * 获取LvArea数据表格excel
	 * 
	 * @param user
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public void download(LvArea lvArea, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvArea,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvArea页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvArea lvArea = new LvArea();
		lvArea.setId(UUID.randomUUID().toString());
		return "/lvarea/lvAreaAdd";
	}

	/**
	 * 添加LvArea
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvArea lvArea) {
		Json j = new Json();		
		lvAreaService.add(lvArea);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvArea查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvArea lvArea = lvAreaService.get(id);
		request.setAttribute("lvArea", lvArea);
		return "/lvarea/lvAreaView";
	}

	/**
	 * 跳转到LvArea修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvArea lvArea = lvAreaService.get(id);
		request.setAttribute("lvArea", lvArea);
		return "/lvarea/lvAreaEdit";
	}

	/**
	 * 修改LvArea
	 * 
	 * @param lvArea
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvArea lvArea) {
		Json j = new Json();		
		lvAreaService.edit(lvArea);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvArea
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvAreaService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 根据父编码获取列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getByParentCode")
	@ResponseBody
	public Json getByParentCode(LvArea lvArea) {
		Json j = new Json();
		j.setObj(lvAreaService.queryAllList(lvArea));
		j.setSuccess(true);
		return j;
	}

}
