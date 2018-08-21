package com.d1m.excute;

import com.aventstack.extentreports.ExtentReports;
import com.d1m.actions.ActionType;
import com.d1m.actions.ByType;
import com.d1m.entity.CaseDetailsEntity;
import com.d1m.entity.ModuleEntity;
import com.d1m.manage.ModuleEntityManager;
import com.d1m.utils.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Auther: Leo.hu
 * @Date: 2018/6/6 14:54
 * @Description:
 */
public class ExcuteAllCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcuteAllCase.class);

    private static List<ModuleEntity> moduleEntityList;

    List<CaseDetailsEntity> caseDetailsEntityList;

    ModuleEntityManager moduleEntityManager;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        ExcuteAllCase.driver = driver;
    }

    private static WebDriver driver;

    /* 操作枚举类型 */
    private ActionType actionType;

    /* 操作枚举类型 */
    private ActionType actionKey;

    /* 页面元素 */
    private WebElement webElement;

    private SoftAssert softAssert;

    /**
     * 功能描述: 测试执行类
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 15:34
     */
    @Test(dataProvider = "createTestData")
    public void excute(ITestContext context, String moduleName, List<CaseDetailsEntity> caseDetailsEntityList) {
        Reporter.log("-----开始执行测试------");
        //开始执行某一模块时打开浏览器
        setDriver(DriverFactory.getInstance().getFirefoxDriver());
        driver.manage().window().maximize();
        //driver.get("https://abao:Bie7M9Oh@preprod.dior.cn/beauty/zh_cn/store/customer/account/login");
        driver.get(context.getCurrentXmlTest().getParameter("url"));
        Reporter.log("当前打开的url为：" + context.getCurrentXmlTest().getParameter("url"));
        softAssert = new SoftAssert();
        //遍历caseDetailsEntityList，挨个执行具体的case
        for (int j = 0; j < caseDetailsEntityList.size(); j++) {
            //如果是第一次执行，则判断是否有前置模块，后面则不再判断
            if (j < 1) {
                //判断是否有前置模块
                if (StringTools.isNullOrEmpty(caseDetailsEntityList.get(0).getPreModule())) {
                    //如果没有前置模块则直接执行
                    Reporter.log("当前模块没有前置模块");
                    excuteCaseDetail(caseDetailsEntityList.get(j));
                } else {
                    //如果有前置模块，则获取所有的前置模块，挨个执行
                    String[] preModule = caseDetailsEntityList.get(0).getPreModule().split(",");
                    for (int k = 0; k < preModule.length; k++) {
                        Reporter.log("执行的前置模块为：" + preModule[k]);
                        excuteSingleModule(preModule[k]);
                    }
                    Reporter.log("前置模块全部执行完毕，开始执行测试模块");
                    //执行完前置模块再执行当前模块
                    excuteCaseDetail(caseDetailsEntityList.get(j));
                }
            } else {
                Reporter.log("无前置模块，开始执行测试模块");
                excuteCaseDetail(caseDetailsEntityList.get(j));
            }
        }
        softAssert.assertAll();
        //一个模块执行完毕之后，关闭浏览器
        driver.quit();
        Reporter.log("-----执行完毕-----");
    }

    /**
     * 功能描述: testNg数据提供者
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/8 10:58
     */
    @DataProvider
    public Object[][] createTestData() {
        //public Object[][] createTestData(ITestContext context) {
        //String path = context.getCurrentXmlTest().getParameter("project");
        //将所有的case按模块名称划分
        moduleEntityManager = new ModuleEntityManager();
        //moduleEntityList = moduleEntityManager.createModuleEntity(path + "/CaseEntity.xls", path + "/ActionEntity.xls", path + "/ElementEntity.xls", path + "/DataEntity.xls");
        moduleEntityList = moduleEntityManager.createModuleEntity("CaseEntity.xls", "ActionEntity.xls", "ElementEntity.xls", "DataEntity.xls");
        Object dateMap[][] = new Object[moduleEntityList.size()][2];
        for (int i = 0; i < moduleEntityList.size(); i++) {
            dateMap[i][0] = moduleEntityList.get(i).getModuleName();
            dateMap[i][1] = moduleEntityList.get(i).getCaseDetailsEntityList();
        }
        return dateMap;
    }


    /**
     * 功能描述: 根据模块名称执行单个的模块
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 15:14
     */
    public void excuteSingleModule(String moduleName) {
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        List<CaseDetailsEntity> moduleCaseDetailsEntityList = new ArrayList<>();
        for (int i = 0; i < moduleEntityList.size(); i++) {
            if (moduleEntityList.get(i).getModuleName().equals(moduleName)) {
                moduleCaseDetailsEntityList = moduleEntityList.get(i).getCaseDetailsEntityList();
                break;
            }
        }
        for (int i = 0; i < moduleCaseDetailsEntityList.size(); i++) {
            excuteCaseDetail(moduleCaseDetailsEntityList.get(i));
        }
    }

    /**
     * 功能描述: 执行具体的case
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 15:19
     */
    public void excuteCaseDetail(CaseDetailsEntity caseDetailsEntity) {
        Reporter.log("当前执行步骤的ID为：" + caseDetailsEntity.getId());
        //操作枚举类型
        actionType = ActionType.getEnumByValue(caseDetailsEntity.getActionType());
        actionKey = ActionType.getEnumByValue(caseDetailsEntity.getActionKey());
        webElement = getElement(driver, caseDetailsEntity);
        Reporter.log("执行的ActionType为：" + actionType + "-----ActionKey为：" + actionKey + "-----被执行的元素为：" + webElement);
        try {
            doAllAction(caseDetailsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: 获取元素
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 14:22
     */
    public WebElement getElement(WebDriver driver, CaseDetailsEntity caseDetailsEntity) {
        //根据LocationType确定具体的查找方式
        if (caseDetailsEntity.getElementLocationType() == null) {
            return null;
        } else {
            By by = toBy(caseDetailsEntity);
            // 添加等待时间,获得元素
            try {
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(ExpectedConditions.presenceOfElementLocated(by));
                List<WebElement> elements = driver.findElements(by);
                if (elements.size() > 0) {
                    return elements.get(0);
                } else {
                    return null;
                }
            } catch (ElementNotVisibleException e) {
                Reporter.log("元素：" + caseDetailsEntity.getElementPath() + "不可见" + e.getMessage());
                softAssert.assertTrue(false, "元素：" + caseDetailsEntity.getElementPath() + "不可见" + e.getMessage());
                return null;
            } catch (NoSuchElementException e) {
                Reporter.log("元素：" + caseDetailsEntity.getElementPath() + "不存在" + e.getMessage());
                softAssert.assertTrue(false, "元素：" + caseDetailsEntity.getElementPath() + "不存在" + e.getMessage());
                return null;
            } catch (TimeoutException e) {
                Reporter.log("元素：" + caseDetailsEntity.getElementPath() + "查询超时" + e.getMessage());
                softAssert.assertTrue(false, "元素：" + caseDetailsEntity.getElementPath() + "查询超时" + e.getMessage());
                return null;
            }
        }
    }

    /**
     * 功能描述: 获取操作元素的方式
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 14:22
     */
    private By toBy(CaseDetailsEntity caseDetailsEntity) {
        String locationType = caseDetailsEntity.getElementLocationType();
        String path = caseDetailsEntity.getElementPath();
        switch (locationType) {
            case ByType.ID:
                return By.id(path);
            case ByType.NAME:
                return By.name(path);
            case ByType.XPATH:
                return By.xpath(path);
            case ByType.CSSSELECTOR:
                return By.cssSelector(path);
            case ByType.CLASSNAME:
                return By.className(path);
            case ByType.LINKTEXT:
                return By.linkText(path);
            case ByType.PARTIALLINKTEXT:
                return By.partialLinkText(path);
            case ByType.TAGNAME:
                return By.tagName(path);
            default:
                Reporter.log("未匹配到元素类型，默认使用xpath查找元素");
                return By.xpath(path);
        }
    }

    /**
     * 功能描述: 执行所有的ActionEntity
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 15:45
     */
    private void doAllAction(CaseDetailsEntity caseDetailsEntity) throws Exception {
        switch (actionType) {
            case ACTIONS:
                actionsAction(caseDetailsEntity);
                break;
            case CHECK:
                executeCheckAction(caseDetailsEntity);
                break;
            case SWITCH:
                switchAction();
                break;
            case JAVASCRIPT:
                javaScriptAction(caseDetailsEntity);
                break;
            case KEYBOARD:
                pressKeyboard();
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: actions Action的具体操作
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:45
     */
    private void actionsAction(CaseDetailsEntity caseDetailsEntity) {
        Actions actions = new Actions(driver);
        Alert alert;
        switch (actionKey) {
            case CLICK:
                try {
                    webElement.click();
                    break;
                } catch (Exception e) {
                    Reporter.log("点击" + webElement + "时发生异常：" + e);
                }
            case IMPLICIT_CLICK:
                JavascriptExecutor js_implicit = (JavascriptExecutor) driver;
                js_implicit.executeScript("arguments[0].style.display=\"block\";", webElement);
                webElement.click();
                break;
            case SENDKEY:
                webElement.clear();
                actions.sendKeys(webElement, caseDetailsEntity.getElementData()).build().perform();
                break;
            case MOVE:
                actions.moveToElement(webElement).build().perform();
                break;
            case RIGHTCLICK:
                actions.contextClick(webElement).perform();
                break;
            case DOUBLECLICK:
                actions.doubleClick(webElement).perform();
                break;
            case DRAG:
                //actions.dragAndDrop(webElement, targetElement);
                break;
            case ACCEPTALERT:
                alert = driver.switchTo().alert();
                alert.accept();
                break;
            case CANCELALERT:
                alert = driver.switchTo().alert();
                alert.dismiss();
                break;
            case GETALERTTEXT:
                alert = driver.switchTo().alert();
                alert.getText();
                break;
            default:
                actions.release();
                break;
        }
    }

    /**
     * 功能描述: checkAction的具体操作
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:44
     */
    private void executeCheckAction(CaseDetailsEntity caseDetailsEntity) {
        switch (actionKey) {
            case EQUALTEXT:
                checkText(caseDetailsEntity, true);
                break;
            case NOTEQUALTEXT:
                checkText(caseDetailsEntity, false);
                break;
            case EQUALHREFVALUE:
                checkHrefValue(caseDetailsEntity, true);
                break;
            case NOTEQUALHREFVALUE:
                checkHrefValue(caseDetailsEntity, false);
                break;
            case ELEMENTEXIST:
                checkElementIsExist(caseDetailsEntity, true);
                break;
            case ELEMENTNOTEXIST:
                checkElementIsExist(caseDetailsEntity, false);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: 判断元素是否存在
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/12 18:12
     */
    private void checkElementIsExist(CaseDetailsEntity caseDetailsEntity, Boolean b) {
        //判断预期元素存在
        if (b) {
            if (webElement == null) {
                softAssert.assertTrue(false, "当前caseId为：" + caseDetailsEntity.getId() + "预期元素:" + caseDetailsEntity.getElementPath() + "期望存在，实际不存在");
            } else {
                softAssert.assertTrue(true, "当前caseId为：" + caseDetailsEntity.getId() + "预期元素:" + caseDetailsEntity.getElementPath() + "期望存在，实际存在");
            }
        } else {
            //判断预期元素不存在
            if (webElement == null) {
                softAssert.assertTrue(true, "当前caseId为：" + caseDetailsEntity.getId() + "预期元素：" + caseDetailsEntity.getElementPath() + "，期望不存在，实际不存在");
            } else {
                softAssert.assertTrue(false, "当前caseId为：" + caseDetailsEntity.getId() + "预期元素:" + caseDetailsEntity.getElementPath() + "期望不存在，实际存在");
            }
        }
    }

    /**
     * 功能描述: 校验值是否相等
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:44
     */
    private void checkText(CaseDetailsEntity caseDetailsEntity, boolean equalText) {
        String text = webElement.getText();
        // 校验元素Text属性和给定的值相等
        equalvalue(caseDetailsEntity, equalText, text);
    }

    /**
     * 功能描述: 校验href是否相等
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:44
     */
    private void checkHrefValue(CaseDetailsEntity caseDetailsEntity, boolean equalHrefValue) {
        String attributeValue = webElement.getAttribute("href");
        // 校验元素herf属性值和给定的值相等
        equalvalue(caseDetailsEntity, equalHrefValue, attributeValue);
    }

    /**
     * 功能描述: 校验元素值是否相等
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:43
     */
    private void equalvalue(CaseDetailsEntity caseDetailsEntity, boolean equalHrefValue, String attributeValue) {
        if (equalHrefValue) {
            if (!attributeValue.equals(caseDetailsEntity.getElementData())) {
                softAssert.assertTrue(false, "当前caseId为：" + caseDetailsEntity.getId() + "预期值为：" + caseDetailsEntity.getElementData() + "实际值为：" + attributeValue);
            } else {
                softAssert.assertTrue(true, "当前caseId为：" + caseDetailsEntity.getId() + "预期值为：" + caseDetailsEntity.getElementData() + "实际值为：" + attributeValue);
            }
        } else {
            // 校验元素herf属性值和给定的值不相等
            if (attributeValue.equals(caseDetailsEntity.getElementData())) {
                softAssert.assertTrue(false, "当前caseId为：" + caseDetailsEntity.getId() + "预期值为：" + caseDetailsEntity.getElementData() + "实际值为：" + attributeValue);
            } else {
                softAssert.assertTrue(true, "当前caseId为：" + caseDetailsEntity.getId() + "预期值为：" + caseDetailsEntity.getElementData() + "实际值为：" + attributeValue);
            }
        }
    }

    /**
     * 功能描述: switchAction的具体操作
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:43
     */
    private void switchAction() {
        switch (actionKey) {
            case SWITCHWINDOW:
                // 进入新窗口，关闭其他窗口
                windowSwitchToNew(driver, true);
                break;
            case SWITCHTOFRAME:
                // 切换frame
                frameSwitch(driver, webElement);
                break;
            case SWITCHTODEFRAME:
                // 切换到默认frame
                driver.switchTo().defaultContent();
                break;
            case CLOSENEWWINDOW:
                // 关闭当前窗口打开的最新窗口
                windowCloseNew(driver);
                break;
            default:
                break;
        }
    }

    /**
     * 功能描述: 根据windowHandler切换到新的窗口中
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:42
     */
    public void windowSwitchToNew(WebDriver driver, boolean closeOldWindow) {
        String currentWindow = driver.getWindowHandle();
        try {
            List<String> winIdList = new ArrayList<>(driver.getWindowHandles());
            if (winIdList.size() > 1) {
                winIdList.remove(currentWindow);
                if (closeOldWindow) {
                    driver.close();
                }
                driver.switchTo().window(winIdList.get(0));
                if (winIdList.size() > 1) {
                    Reporter.log("There is too many windows,please confirm last page operation is open one window");
                }
            } else {
                Reporter.log("There is no more window,please check the explore is regular work!");
            }
        } catch (NoSuchWindowException e) {
            Reporter.log("未找到新窗口！" + e.getSystemInformation());
        }
    }

    /**
     * 功能描述: 根据WebElement切换到新的窗口中
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:41
     */
    public void frameSwitch(WebDriver driver, WebElement frameElement) {
        try {
            driver.switchTo().frame(frameElement);
        } catch (NoSuchFrameException e) {
            Reporter.log("driver.switchTo().frame failed ,element " + frameElement.toString() + "not found ");
        }
    }

    /**
     * 功能描述: 关闭之前打开的新窗口
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:41
     */
    public void windowCloseNew(WebDriver driver) {
        String currentWindow = driver.getWindowHandle();
        try {
            List<String> winIdList = new ArrayList<>(driver.getWindowHandles());
            if (winIdList.size() > 1) {
                driver.switchTo().window(winIdList.get(winIdList.size() - 1));
                driver.close();
                driver.switchTo().window(currentWindow);
                Reporter.log("current window id is " + currentWindow);
                Reporter.log("current window url is " + driver.getCurrentUrl());
                if (winIdList.size() > 1) {
                    Reporter.log("There is too many windows,please confirm last page operation is open one window");
                }
            } else {
                Reporter.log("There is no more window,please check the explore is regular work!");
            }
        } catch (NoSuchWindowException e) {
            Reporter.log("could not find the new window" + e.getSystemInformation());
        }
    }

    /**
     * 功能描述: 对元素执行js操作
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:54
     */
    private void javaScriptAction(CaseDetailsEntity caseDetailsEntity) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript(caseDetailsEntity.getActionKey(), webElement);
    }

    /**
     * 功能描述: 操作键盘上的某个键
     *
     * @param: * @param null
     * @return:
     * @auther: Leo.hu
     * @date: 2018/6/6 17:56
     */
    private void pressKeyboard() {
        Actions actions = new Actions(driver);
        Keys keys = StringToKeys.stringToKeys(actionKey.toString());
        actions.sendKeys(webElement, keys).perform();
    }

}
