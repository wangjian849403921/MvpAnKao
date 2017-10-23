package com.mvpankao.http;

/**
 * 请求url
 * Created by wangjian
 * On  2016/11/14
 */

public class NetUrl {

    //测试接口
    public static final String URLHeader2 = "http://192.168.1.109:8080/zhengsu-platform-api/api/";
    //测试接口2
    public static final String URLHeader1 = "http://192.168.1.29:8080/zhengsu-platform-api/api/";
    //外网服务器
    public static final String URLHeader = "http://47.95.224.168:8080/zhengsu-platform-api/api/";

    public static final String DOCHeader = "http://47.95.224.168:8080/";
    public static final String DOCHeader2 = "http://192.168.1.109:80/";


    /**
     * 账号
     */
    public interface Account {
        /**
         * 登录
         */
        String Login = "login";

    }

    /**
     * 产品
     */
    public interface Product {
        /**
         * 筛选类型
         */
        String TypeSelect = "product/getConditionList";
        /**
         * 产品列表
         */
        String ProductList = "product/getProductListByCondition";
        /**
         * 产品详情
         */
        String ProductDetail = "product/detail";

        /**
         * 提交订单
         */
        String CommitOrder = "product/order";
        /**
         * 提交订单
         */
        String CommitShopCarOrder = "product/shoppingcart/order";

        /**
         * 筛选类型2
         */
        String TypeSelect2 = "product/detailCategorylistByProductId";

        /**
         * 库存量
         */
        String TypeSelect3 = "product/getStockByDetailCondition";

        /**
         * 首页产品
         */
        String HomePageProduct = "init/initProduct";
    }
    public interface MyOrder{

        /*
         * 我的订单列表
         */
        String MyOrder="product/order/list";
        /*
     * 我的订单详情
     */
        String MyOrderDETAIL="product/order/detail";
        /*
     * 我的订单(删除)
     */
        String MyOrderDELETE="product/order/delete";
    }
    /**
     * 查询
     */
    public interface Query {
        /**
         * 报告列表
         */
        String ReportQuery = "report/list";
        /**
         * 资质查询
         */
        String TechnologyQuery = "InstallationTechnology/list";

    }
    /**
     * 工单
     */
    public interface WorkOrder {
        /**
         * 工单列表
         */
        String WorkOrderList = "Engineering/EngineeringList";
        /**
         * 工单详情
         */
        String WorkOrderDetail = "Engineering/EngineeringDetail";
        /**
         * 警报列表
         */
        String WarningList = "Alarm/AlarmList";
        /**
         * 警报详情
         */
        String WarningDetail = "Alarm/AlarmDetail";

        /**
         * 创建工单（资产）
         */
        String AssertList = "Asset/userassetList";

        /**
         * （资产）详情
         */
        String AssertDetail= "Asset/childasset";

        /**
         * 参数信息
         */
        String ParamInfo= "Asset/assetsparm";
        /**
         * 图片更换
         */
        String ParamImageUpdate= "Asset/changeassetsImg";
        /**
         * 执行人列表
         */
        String EXECUTORLIST= "Engineering/EngineeringExecutor";
        /**
         * 创建工单
         */
        String CREATEWORKORDER= "Engineering/saveEngineering";
        /**
         * 工单类型筛选
         */
        String WORKORDERTYPE= "Engineering/EngineeringSelectList";
        /**
         * 警报类型筛选
         */
        String WARNINGTYPE= "Alarm/AlarmType";

        /**
         * 工单类型筛选结果
         */
        String DeleteWorkOrder= "Engineering/deleteEngineering";
        /**
         * 工单日志列表
         */
        String WorkLogList= "Engineering/EngineeringWorkLog";
        /**
         * 添加工单日志
         */
        String AddWorkLog= "Engineering/saveEngineeringLog";
    }

    /**
     * 我的订阅
     */
    public interface MySubscribe {
        /**
         * 订阅列表
         */
        String SubscribeList = "Experiment/year/list";


    }

    /**
     * 工作流
     */
    public interface WorkFlow {
        /**
         * 工作流列表
         */
        String WorkFlowList = "Workflow/list";
        /**
         * 工作流详情
         */
        String WorkFlowDetail = "Workflow/detail";

        /**
         * 上传任务
         */
        String UPLOADTask = "Workflow/stepOperation";

        /**
         * 工作流审核列表
         */
        String WorkFlowStepList = "Workflow/stepOperationLogList";
    }

    /**
     * 故障报修
     */
    public interface Repair {

        /**
         * 故障报修
         */
        String Repair = "Repair/requestRepair";
        /**
         * 故障报修类型
         */
        String RepairType = "Repair/repairTypeList";
        /**
         * 我的报修
         */
        String RepairList = "Repair/repairList";
        /**
         * 报修详情
         */
        String RepairDetail = "Repair/repairDetail";

    }

    /**
     * 反馈
     */
    public interface FeedBack {
        /**
         * 反馈
         */
        String FeedBack = "Feedback/feedback";

    }
    /**
     * 资讯
     */
    public interface NEWS{
        /**
         * 资讯列表
         */
        String NEWSLIST = "official/list";

    }
    /**
     * 地址
     */
    public interface Address {
        /**
         * 地址列表
         */
        String AddressList = "product/receiptAddressList";
        /**
         * 编辑新建地址
         */
        String AddressEdit = "product/changeDefaultReceiptAddress";
        /**
         * 删除地址
         */
        String AddressDelete = "product/deleteReceiptAddress";

    }
    /**
     * 购物车
     */
    public interface ShopCar {
        /**
         * 购物车列表
         */
        String ShopCarList = "product/ShoppingCartList";

        /**
         * 加入购物车
         */
        String InserttoShopCar = "product/insertShoppingCart";
        /**
         * 删除购物车内产品
         */
        String DeleteShopCarProduct = "product/deleteShoppingCart";
    }
}
