package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

import java.io.IOException;

public class ArirangAnalyzerProvider extends AbstractIndexAnalyzerProvider<KoreanAnalyzer> {

    private final KoreanAnalyzer analyzer;

    private boolean bigrammable = true;
    private boolean preserveOrigin = true;
    private boolean exactMatch = false;
    private boolean preserveCNoun = true;
    private boolean wordSegment = true;
    private boolean decompound = true;

    public ArirangAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) throws IOException {
        super(indexSettings, name, settings);

        analyzer = new KoreanAnalyzer();

        bigrammable = settings.getAsBoolean("bigrammable", true);
        decompound = settings.getAsBoolean("decompound", true);
        exactMatch = settings.getAsBoolean("exactMatch", false);
        preserveCNoun = settings.getAsBoolean("preserveCNoun", true);
        preserveOrigin = settings.getAsBoolean("preserveOrigin", true);
        wordSegment = settings.getAsBoolean("wordSegment", true);

        analyzer.setBigrammable(this.bigrammable);
        analyzer.setHasOrigin(this.preserveOrigin);
        analyzer.setOriginCNoun(this.preserveCNoun);
        analyzer.setWordSegment(this.wordSegment);
        analyzer.setDecompound(this.decompound);
        analyzer.setExactMatch(this.exactMatch);
    }

    @Override
    public KoreanAnalyzer get() {
        return this.analyzer;
    }
}