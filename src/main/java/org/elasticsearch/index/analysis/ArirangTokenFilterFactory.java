package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.KoreanFilter;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

public class ArirangTokenFilterFactory extends AbstractTokenFilterFactory {

    private boolean bigrammable;
    private boolean decompound;
    private boolean exactMatch;
    private boolean preserveCNoun;
    private boolean preserveOrigin;

    public ArirangTokenFilterFactory(IndexSettings indexSettings, Environment env, String name, Settings settings) {
        super(indexSettings, name, settings);

        bigrammable = settings.getAsBoolean("bigrammable", false);
        bigrammable = settings.getAsBoolean("bigrammable", true);
        decompound = settings.getAsBoolean("decompound", true);
        exactMatch = settings.getAsBoolean("exactMatch", false);
        preserveCNoun = settings.getAsBoolean("preserveCNoun", true);
        preserveOrigin = settings.getAsBoolean("preserveOrigin", true);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        return new KoreanFilter(tokenStream, bigrammable, preserveOrigin, exactMatch, preserveCNoun, false, decompound);
    }
}