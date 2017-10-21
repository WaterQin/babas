package cn.itcast.core.controller;

import cn.itcast.common.encode.Md5Pwd;
import cn.itcast.common.web.ResponseUtils;
import cn.itcast.common.web.session.SessionProvider;
import cn.itcast.core.bean.country.City;
import cn.itcast.core.bean.country.Province;
import cn.itcast.core.bean.country.Town;
import cn.itcast.core.bean.user.Addr;
import cn.itcast.core.bean.user.Buyer;
import cn.itcast.core.query.country.CityQuery;
import cn.itcast.core.query.country.TownQuery;
import cn.itcast.core.query.user.AddrQuery;
import cn.itcast.core.service.country.CityService;
import cn.itcast.core.service.country.ProvinceService;
import cn.itcast.core.service.country.TownService;
import cn.itcast.core.service.user.AddrService;
import cn.itcast.core.service.user.BuyerService;
import cn.itcast.core.web.Constants;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 跳转登陆页面
 * 登陆
 * 个人资料
 * 收货地址
 *
 * @author lx
 */
@Controller
public class ProfileController {

    @Autowired
    private SessionProvider sessionProvider;
    @Autowired
    private Md5Pwd md5Pwd;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private ImageCaptchaService imageCaptchaService;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private TownService townService;
    @Autowired
    private AddrService addrService;

    //GET请求
    @RequestMapping(value = "/shopping/login.shtml", method = RequestMethod.GET)
    public String login() {
        return "buyer/login";
    }
    //POST请求

    /**
     * 1:验证码是否为null
     * 2:验证码  是否正确
     * 3:用户是否为NUll
     * 4:密码是否为NUll
     * 5:用户是否正确
     * 6密码是否正确
     * Md5  纯生Md5
     * 放进Session   跳转ReturnUrl
     *
     * @param buyer
     * @param captcha
     * @param returnUrl
     * @return
     */
    @RequestMapping(value = "/shopping/login.shtml", method = RequestMethod.POST)
    public String login(Buyer buyer, String captcha, String returnUrl, HttpServletRequest request, RedirectAttributes model) {
        //验证码是否为null
        if (StringUtils.isNotBlank(captcha)) {
            //1:JSESSIONID
            //2验证码
            if (imageCaptchaService.validateResponseForID(sessionProvider.getSessionId(request), captcha)) {
                if (null != buyer && StringUtils.isNotBlank(buyer.getUsername())) {
                    if (StringUtils.isNotBlank(buyer.getPassword())) {
                        Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
                        if (null != b) {
                            //
                            if (b.getPassword().equals(md5Pwd.encode(buyer.getPassword()))) {
                                //把用户对象放在Session
                                sessionProvider.setAttribute(request, Constants.BUYER_SESSION, b);
                                if (StringUtils.isNotBlank(returnUrl)) {
                                    return "redirect:" + returnUrl;
                                } else {
                                    //个人中心
                                    //model.addFlashAttribute("buyer", b);
                                    //model.addAttribute("buyer",buyer);
                                    return "redirect:/buyer/index.shtml?";
                                }
                            } else {
                                model.addAttribute("error", "密码错误");
                            }
                        } else {
                            model.addAttribute("error", "用户名输入错误");
                        }
                    } else {
                        model.addAttribute("error", "请输入密码");
                    }
                } else {
                    model.addAttribute("error", "请输入用户名");
                }
            } else {
                model.addAttribute("error", "验证码输入错误");
            }
        } else {
            model.addAttribute("error", "请填写验证码");
        }
        return "buyer/login";
    }

    //个人中心
    @RequestMapping(value = "/buyer/index.shtml")
    public String index(@ModelAttribute("buyer") Buyer buyer, ModelMap model) {
        return "buyer/index";
    }

    //退出登陆
    @RequestMapping(value = "/shopping/logout.shtml")
    public String logout(String returnUrl, HttpServletRequest request, HttpServletResponse response) {
        sessionProvider.logout(request);
        return "redirect:/shopping/login.shtml";
    }

    //跳转到个人资料界面
    @RequestMapping(value = "/buyer/profile.shtml")
    public String profile(HttpServletRequest request, ModelMap model) {
        //加载用户
        Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
        Buyer b = buyerService.getBuyerByKey(buyer.getUsername());
        model.addAttribute("buyer", b);
        //加载市
        CityQuery cityQuery = new CityQuery();
        cityQuery.setProvince(b.getProvince());
        List<City> cities = cityService.getCityList(cityQuery);
        model.addAttribute("citys", cities);
        //加载省份
        List<Province> provinces = provinceService.getProvinceList(null);
        model.addAttribute("provinces", provinces);
        //加载县
        TownQuery townQuery = new TownQuery();
        townQuery.setCity(b.getCity());
        List<Town> towns = townService.getTownList(townQuery);
        model.addAttribute("towns", towns);
        return "buyer/profile";
    }

    //查询省份
    @RequestMapping(value = "/buyer/province.shtml")
    public void province(String code, HttpServletResponse response) {
        List<Province> provinces = provinceService.getProvinceList(null);
        JSONObject jo = new JSONObject();
        jo.put("provinces", provinces);
        ResponseUtils.renderJson(response, jo.toString());
    }

    //修改省份
    @RequestMapping(value = "/buyer/city.shtml")
    public void city(String code, HttpServletResponse response) {
        CityQuery cityQuery = new CityQuery();
        cityQuery.setProvince(code);
        List<City> citys = cityService.getCityList(cityQuery);
        JSONObject jo = new JSONObject();
        jo.put("citys", citys);
        ResponseUtils.renderJson(response, jo.toString());

    }

    //修改城市
    @RequestMapping(value = "/buyer/town.shtml")
    public void town(String code, HttpServletResponse response) {
        TownQuery townQuery = new TownQuery();
        townQuery.setCity(code);
        List<Town> towns = townService.getTownList(townQuery);
        JSONObject jo = new JSONObject();
        jo.put("towns", towns);
        ResponseUtils.renderJson(response, jo.toString());
    }

    //保存个人资料
    @RequestMapping(value = "/buyer/o_profile.shtml")
    public void o_profile(Buyer buyer, HttpServletResponse response) {
        buyerService.updateBuyerByKey(buyer);
        JSONObject jo = new JSONObject();
        jo.put("message", "修改成功");
        ResponseUtils.renderJson(response, jo.toString());

    }

    //跳转收货地址
    @RequestMapping(value = "/buyer/deliver_address.shtml")
    public String deliver_address(@RequestParam(value = "username", required = false) String username, HttpServletRequest request, ModelMap model) {
        //加载已有的地址
        //加载已有地址
        Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
        //设置地址条件对象
        AddrQuery addrQuery = new AddrQuery();
        addrQuery.setBuyerId(buyer.getUsername());
        List<Addr> addrs = addrService.getAddrList(addrQuery);

        model.addAttribute("addrs", addrs);
        return "buyer/deliver_address";
    }

    //保存新建地址
    @RequestMapping(value = "/buyer/deliverAddr.shtml")
    public void delieveAddr(Addr addr, HttpServletResponse response, HttpServletRequest request) {

        JSONObject jo = new JSONObject();
        //根据Id是否为Null为判断 是修改还是保存
        if (null != addr && null != addr.getId()) {
            //修改
            addrService.updateAddrByKey(addr);
            jo.put("message", "修改成功!");

        } else {
            //保存
            Buyer buyer = (Buyer) sessionProvider.getAttribute(request, Constants.BUYER_SESSION);
            addr.setBuyerId(buyer.getUsername());
            addrService.addAddr(addr);
            jo.put("message", "保存成功!");
        }
        ResponseUtils.renderJson(response, jo.toString());
    }
}
