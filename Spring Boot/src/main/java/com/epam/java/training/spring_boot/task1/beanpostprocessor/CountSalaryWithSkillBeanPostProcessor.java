package com.epam.java.training.spring_boot.task1.beanpostprocessor;

import com.epam.java.training.spring_boot.task1.model.Position;
import com.epam.java.training.spring_boot.task1.model.Salary;
import com.epam.java.training.spring_boot.task1.model.Skill;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class CountSalaryWithSkillBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {

        try {
            if (Position.class == bean.getClass()) {
                //Give Salary and Skills
                Field skillsBean = bean.getClass().getDeclaredField("skills");
                Field salaryBean = bean.getClass().getDeclaredField("salary");
                skillsBean.setAccessible(true);
                salaryBean.setAccessible(true);
                //Get vars from fields
                List<Skill> listSkills = (List<Skill>) skillsBean.get(bean);
                Salary salary = (Salary) salaryBean.get(bean);

                //Sum skills on rating(rait = 1% salary)
                int sum = listSkills.stream()
                        .mapToInt(Skill::getRating)
                        .sum();
                Integer nativeSalary = salary.getSalaryInDollars();
                int finalSum = nativeSalary * (100 + sum) / 100;
                salary.setSalaryInDollars(finalSum);
                salaryBean.set(bean, salary);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
