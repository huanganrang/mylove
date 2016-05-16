package jb.controller;

import com.alibaba.fastjson.JSON;
import jb.pageModel.*;
import jb.service.LvBoostRecordServiceI;
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
 * LvBoostRecord管理控制器
 * 
 * @author John
 * 
 */
@Controller
@RequestMapping("/lvBoostRecordController")
public class LvBoostRecordController extends BaseController {

	@Autowired
	private LvBoostRecordServiceI lvBoostRecordService;


	/**
	 * 跳转到LvBoostRecord管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		return "/lvboostrecord/lvBoostRecord";
	}

	/**
	 * 获取LvBoostRecord数据表格
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/dataGrid")
	@ResponseBody
	public DataGrid dataGrid(LvBoostRecord lvBoostRecord, PageHelper ph) {
		return lvBoostRecordService.dataGrid(lvBoostRecord, ph);
	}
	/**
	 * 获取LvBoostRecord数据表格excel
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
	public void download(LvBoostRecord lvBoostRecord, PageHelper ph,String downloadFields,HttpServletResponse response) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException{
		DataGrid dg = dataGrid(lvBoostRecord,ph);		
		downloadFields = downloadFields.replace("&quot;", "\"");
		downloadFields = downloadFields.substring(1,downloadFields.length()-1);
		List<Colum> colums = JSON.parseArray(downloadFields, Colum.class);
		downloadTable(colums, dg, response);
	}
	/**
	 * 跳转到添加LvBoostRecord页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		LvBoostRecord lvBoostRecord = new LvBoostRecord();
		lvBoostRecord.setId(UUID.randomUUID().toString());
		return "/lvboostrecord/lvBoostRecordAdd";
	}

	/**
	 * 添加LvBoostRecord
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(LvBoostRecord lvBoostRecord) {
		Json j = new Json();		
		lvBoostRecordService.add(lvBoostRecord);
		j.setSuccess(true);
		j.setMsg("添加成功！");		
		return j;
	}

	/**
	 * 跳转到LvBoostRecord查看页面
	 * 
	 * @return
	 */
	@RequestMapping("/view")
	public String view(HttpServletRequest request, String id) {
		LvBoostRecord lvBoostRecord = lvBoostRecordService.get(id);
		request.setAttribute("lvBoostRecord", lvBoostRecord);
		return "/lvboostrecord/lvBoostRecordView";
	}

	/**
	 * 跳转到LvBoostRecord修改页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		LvBoostRecord lvBoostRecord = lvBoostRecordService.get(id);
		request.setAttribute("lvBoostRecord", lvBoostRecord);
		return "/lvboostrecord/lvBoostRecordEdit";
	}

	/**
	 * 修改LvBoostRecord
	 * 
	 * @param lvBoostRecord
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(LvBoostRecord lvBoostRecord) {
		Json j = new Json();		
		lvBoostRecordService.edit(lvBoostRecord);
		j.setSuccess(true);
		j.setMsg("编辑成功！");		
		return j;
	}

	/**
	 * 删除LvBoostRecord
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		lvBoostRecordService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

}
