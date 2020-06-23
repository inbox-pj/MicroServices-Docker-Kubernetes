### Usage

* Setup `minikube' before running this application`
```
minikube stop
minikube delete
minikube minikube start --vm-driver=virtualbox --memory='4000mb' 
```

* Spring Cloud Kubernetes requires access to the Kubernetes API in order to be able to retrieve a list of addresses for pods 
running for a single service. If you use Kubernetes, you should just execute the following command:
```
kubectl create clusterrolebinding admin --clusterrole=cluster-admin --serviceaccount=default:default
```

* Build Maven project with using command: `mvn clean install`

* Build Docker images for each module using command, for example: `docker build -t pjaiswal10/pricing-service:1.0 .`

* Push the docker image to repository hub using `docker push pjaiswal10/pricing-service:1.0`

* Now to go  `/kubernetes` directory and Apply all templates to Minikube using following command:
```
kubectl apply -f mongodb-configmap.yaml 
kubectl apply -f mongodb-secret.yaml 
kubectl apply -f mongodb-deployment.yaml 
kubectl apply -f pricing-service-deployment.yaml 
kubectl apply -f catalog-service-deployment.yaml 
kubectl apply -f gateway-service-deployment.yaml 
kubectl apply -f ingress.yaml 
```

* Check all the kubernetes configuration:
```
[iid@iid Kubernetes]$ kubectl get all

NAME                                   READY   STATUS    RESTARTS   AGE
pod/catalog-service-bb48cff59-smmwj    1/1     Running   0          60m
pod/gateway-service-5c49c489cd-b6nl5   1/1     Running   0          54m
pod/mongodb-8699d495fb-hvflt           1/1     Running   0          70m
pod/pricing-service-67bff7d44f-xxr8j   1/1     Running   0          61m

NAME                      TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)          AGE
service/catalog-service   NodePort    10.100.158.60    <none>        8300:31219/TCP   60m
service/gateway-service   NodePort    10.97.28.71      <none>        8100:30441/TCP   54m
service/kubernetes        ClusterIP   10.96.0.1        <none>        443/TCP          90m
service/mongodb           ClusterIP   10.106.117.241   <none>        27017/TCP        70m
service/pricing-service   NodePort    10.103.90.138    <none>        8200:31909/TCP   61m

NAME                              READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/catalog-service   1/1     1            1           60m
deployment.apps/gateway-service   1/1     1            1           54m
deployment.apps/mongodb           1/1     1            1           70m
deployment.apps/pricing-service   1/1     1            1           61m

NAME                                         DESIRED   CURRENT   READY   AGE
replicaset.apps/catalog-service-bb48cff59    1         1         1       60m
replicaset.apps/gateway-service-5c49c489cd   1         1         1       54m
replicaset.apps/mongodb-8699d495fb           1         1         1       70m
replicaset.apps/pricing-service-67bff7d44f   1         1         1       61m

[iid@iid Kubernetes]$ kubectl describe ing ingress-gateway
Name:             ingress-gateway
Namespace:        default
Address:          
Default backend:  gateway-service:8100 (172.17.0.7:8100)
Rules:
  Host                Path  Backends
  ----                ----  --------
  host.clear2pay.com  
                      /pricing   pricing-service:8200 (172.17.0.5:8200)
                      /catalog   catalog-service:8300 (172.17.0.6:8300)
Annotations:          nginx.ingress.kubernetes.io/rewrite-target: /
Events:               <none>
```

* Use the `minikube` service to get the gateway url: 
```
[iid@iid Kubernetes]$ minikube service gateway-service --url
http://192.168.99.106:30441
```

* Open the below swagger UI and call the REST service:

```
http://192.168.99.106:30441/swagger-ui.html
```

* Additionally you can see the kubernetes dashboard to monitor all the resources:

```
minikube dashboard
```

### Reference: 
https://hub.docker.com/u/pjaiswal10