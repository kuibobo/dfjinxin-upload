package io.dfjinxin.handler;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);

    @Override
    public void handleTemplateException(TemplateException e, Environment environment, Writer writer) throws TemplateException {
        logger.error(e.getMessage(), e);
    }
}
