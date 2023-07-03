package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

import java.io.IOException;

public class ArirangAnalyzerProvider extends AbstractIndexAnalyzerProvider<KoreanAnalyzer> {

    private final KoreanAnalyzer analyzer;

    public ArirangAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) throws IOException {
        super(indexSettings, name, settings);

        boolean bigrammable = settings.getAsBoolean("bigrammable", false);
        boolean preserveOrigin = settings.getAsBoolean("preserveOrigin", false);
        boolean exactMatch = settings.getAsBoolean("exactMatch", false);
        boolean preserveCNoun = settings.getAsBoolean("preserveCNoun", true);
        boolean decompound = settings.getAsBoolean("decompound", true);
        boolean wordSegment = settings.getAsBoolean("wordSegment", true);

        analyzer = new KoreanAnalyzer();

        analyzer.setBigrammable(bigrammable);
        analyzer.setHasOrigin(preserveOrigin);
        analyzer.setExactMatch(exactMatch);
        analyzer.setOriginCNoun(preserveCNoun);
        analyzer.setDecompound(decompound);
        analyzer.setWordSegment(wordSegment);

    }

    @Override
    public KoreanAnalyzer get() {
        return this.analyzer;
    }
}