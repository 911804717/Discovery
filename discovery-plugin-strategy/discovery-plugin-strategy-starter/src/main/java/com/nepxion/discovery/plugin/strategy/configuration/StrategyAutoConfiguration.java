package com.nepxion.discovery.plugin.strategy.configuration;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.TypeComparator;

import com.nepxion.discovery.plugin.strategy.adapter.DefaultStrategyVersionFilterAdapter;
import com.nepxion.discovery.plugin.strategy.adapter.StrategyVersionFilterAdapter;
import com.nepxion.discovery.plugin.strategy.condition.DefaultStrategyTypeComparor;
import com.nepxion.discovery.plugin.strategy.condition.ExpressionStrategyCondition;
import com.nepxion.discovery.plugin.strategy.condition.StrategyCondition;
import com.nepxion.discovery.plugin.strategy.constant.StrategyConstant;
import com.nepxion.discovery.plugin.strategy.filter.StrategyVersionFilter;
import com.nepxion.discovery.plugin.strategy.matcher.DiscoveryAntPathMatcherStrategy;
import com.nepxion.discovery.plugin.strategy.matcher.DiscoveryMatcherStrategy;
import com.nepxion.discovery.plugin.strategy.monitor.DefaultStrategyLogger;
import com.nepxion.discovery.plugin.strategy.monitor.StrategyLogger;
import com.nepxion.discovery.plugin.strategy.monitor.StrategyMonitorContext;
import com.nepxion.discovery.plugin.strategy.wrapper.StrategyWrapper;

@Configuration
@ConditionalOnProperty(value = StrategyConstant.SPRING_APPLICATION_STRATEGY_CONTROL_ENABLED, matchIfMissing = true)
public class StrategyAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public DiscoveryMatcherStrategy discoveryMatcherStrategy() {
        return new DiscoveryAntPathMatcherStrategy();
    }

    @Bean
    @ConditionalOnMissingBean
    public StrategyVersionFilter strategyVersionFilter() {
        return new StrategyVersionFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public StrategyVersionFilterAdapter strategyVersionFilterAdapter() {
        return new DefaultStrategyVersionFilterAdapter();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = StrategyConstant.SPRING_APPLICATION_STRATEGY_MONITOR_ENABLED, matchIfMissing = false)
    public StrategyMonitorContext strategyMonitorContext() {
        return new StrategyMonitorContext();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = StrategyConstant.SPRING_APPLICATION_STRATEGY_MONITOR_ENABLED, matchIfMissing = false)
    public StrategyLogger strategyLogger() {
        return new DefaultStrategyLogger();
    }

    @Bean
    @ConditionalOnMissingBean
    public StrategyWrapper strategyWrapper() {
        return new StrategyWrapper();
    }

    @Bean
    @ConditionalOnMissingBean
    public TypeComparator strategyTypeComparator() {
        return new DefaultStrategyTypeComparor();
    }

    @Bean
    @ConditionalOnMissingBean
    public StrategyCondition strategyCondition() {
        return new ExpressionStrategyCondition();
    }
}