
If your Zuul server is also EurekaClient (use @EnableDiscoveryClient annotation) it will discover all the services and
automatically will create routes.

Will list all routes
curl -v http://localhost:7070/route

And this will forward to particular service. It will also automatically load balance calls for you if services run on
multiple machines.

