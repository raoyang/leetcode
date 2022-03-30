package org.sunshine.lc.test;

import com.authine.h3yun.engine.controller.constant.Constant;
import com.authine.h3yun.engine.controller.utils.JsonUtils;
import com.authine.h3yun.engine.controller.utils.K8sServiceUtils;
import com.authine.lateinos.comm.logger.AppLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.ArrayList;
import java.util.List;

public class SwitchTest {
    public static void main(String args[]){

        try(KubernetesClient client = new DefaultKubernetesClient()){
            System.out.println(client);
            Service service = client.services().inNamespace(Constant.K8S_NAMESPACE)
                    .withName("h3yun-engine-traffic-proxy-svc").get();
            String ip = service.getSpec().getClusterIP();
            AppLog.d("获取到的代理器ip地址:" + ip);

            Endpoints endpoints = client.endpoints().inNamespace(Constant.K8S_NAMESPACE)
                    .withName(K8sServiceUtils.getServiceName("shard4")).get();

            EndpointSubset endpointSubset = endpoints.getSubsets().get(0);
            EndpointAddress address = endpointSubset.getAddresses().get(0);
            address.setIp(ip); //第一步获取到的ip
            List<EndpointPort> ports = new ArrayList<>();
            ports.add(new EndpointPortBuilder().
                    withName("proxy").
                    withProtocol("TCP").
                    withPort(80).
                    build());
            endpointSubset.setPorts(ports); //重新设置端口列表

            try {
                String endpoints_str = JsonUtils.object2String(endpoints);
                AppLog.d("shard:" + "shard4" + " endpoints:" + endpoints_str);
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }

            client.endpoints().inNamespace(Constant.K8S_NAMESPACE)
                    .withName(K8sServiceUtils.getServiceName("shard4")).replace(endpoints);
        }


    }
}
