package com.vkea.sbs.config;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@Configuration
//@EnablePrometheusEndpoint
//https://dzone.com/articles/monitoring-using-spring-boot-2-prometheus-and-graf
public class PrometheusConfiguration {

    private MetricRegistry dropwizardMetricRegistry;

    @Autowired
    public PrometheusConfiguration(MetricRegistry dropwizardMetricRegistry) {


        int[][] or = new int[3][2];
        or[1][1] = 100;
        or[1][0] = 150;
        or[2][1] = 200;
        or[2][0] = 250;
        int[][] srd = new int[3][2];
        srd[1] = or[2];
        or[2] = srd[0];
        int[] result = new int[6];
        Integer[] abc = Arrays.stream(result).boxed().toArray(Integer[]::new);
        this.dropwizardMetricRegistry = dropwizardMetricRegistry;
    }

    @PostConstruct
    public void registerPrometheusCollectors() {
        CollectorRegistry.defaultRegistry.clear();
        new StandardExports().register();
        new MemoryPoolsExports().register();
        new DropwizardExports(dropwizardMetricRegistry).register();
    }
}