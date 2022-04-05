package com.futuredreamtraveller.demo.ServiceImpl;

import com.futuredreamtraveller.demo.Entity.Benefit;
import com.futuredreamtraveller.demo.Mapper.BenefitMapper;
import com.futuredreamtraveller.demo.Mapper.EmissionMapper;
import com.futuredreamtraveller.demo.Service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;

@Slf4j
@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Resource
    BenefitMapper benefitMapper;
    @Resource
    EmissionMapper emissionMapper;
    public String calculatorTheBenefit(double num,String type){
        log.debug("enter into CalculatorSerice calculate the benefits");
        StringBuilder resultSb = new StringBuilder();
        resultSb.append("Your cost is equal to"+"\n");
        double emissionNum = 0;
        try{
            emissionNum = emissionMapper.getEmissionByName(type);
        }
        catch (Exception e){
            log.info(e+"");
            return "can not work now,please try again later";
        }
        num=num*emissionNum/1000;
        log.info("emission: "+emissionNum);
//        try{
//            Benefit[] benefits= benefitMapper.getAllBenefit();
//            for(int i =0;i<benefits.length;i++){
//                String result = String .format("%.2f",num/benefits[i].getCO2());
//                resultSb.append("material: "+benefits[i].getMaterial()+" weight: "+result+"\n");
//            }
//        }
//        catch(Exception e){
//            log.info("can not find the database data.please check again");
//            log.info(e+"");
//            return "can not work now,please try again later";
//        }
        String num1 = String .format("%.2f",num);
        String num2 = String .format("%.2f",num*1.7);
        String num3 = String .format("%.2f",num*0.096);
        resultSb.append("the CO2 cost is: "+num1+"kg"+"\n");
        resultSb.append("equals to use water: "+num2+"g"+"\n");
        resultSb.append("equals a car run on the way: "+num3+"day"+"\n");
        return resultSb.toString();
    }
}
