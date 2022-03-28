package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.PunctuationDelimitFilter;
import org.apache.lucene.analysis.ko.WordSegmentFilter;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class ArirangWordSegmentFilterFactory extends AbstractTokenFilterFactory {

    private boolean preserveOrigin;

    public ArirangWordSegmentFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);

        preserveOrigin = settings.getAsBoolean("preserveOrigin", true);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new WordSegmentFilter(tokenStream, preserveOrigin);
    }
}