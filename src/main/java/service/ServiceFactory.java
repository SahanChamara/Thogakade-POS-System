package service;

import service.custom.impl.*;
import utill.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public<T extends SuperService> T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case CUSTOMER:return (T) CustomerServiceImpl.getInstance();
            case ITEM:return (T) ItemServiceImpl.getInstance();
            case ORDERS:return (T) OrderServiceImpl.getInstance();
            case ORDERDETAIL: return (T) OrderDetailServiceImpl.getInstance();
            case LOGIN:return (T) LoginServiceImpl.getInstance();
        }
        return null;
    }
}
