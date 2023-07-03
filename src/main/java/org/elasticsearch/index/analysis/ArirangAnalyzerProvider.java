package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;

import java.io.IOException;

public class ArirangAnalyzerProvider extends AbstractIndexAnalyzerProvider<KoreanAnalyzer> {

    private final KoreanAnalyzer analyzer;

    private boolean bigrammable = false;
    private boolean preserveOrigin = false;
    private boolean exactMatch = false;
    private boolean preserveCNoun = true;
    private boolean decompound = true;
    private boolean wordSegment = true;

    public ArirangAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings) throws IOException {
        super(indexSettings, name, settings);

        bigrammable = settings.getAsBoolean("bigrammable", false);
        preserveOrigin = settings.getAsBoolean("preserveOrigin", false);
        exactMatch = settings.getAsBoolean("exactMatch", false);
        preserveCNoun = settings.getAsBoolean("preserveCNoun", true);
        decompound = settings.getAsBoolean("decompound", true);
        wordSegment = settings.getAsBoolean("wordSegment", true);

        analyzer = new KoreanAnalyzer();

        analyzer.setBigrammable(this.bigrammable);
        analyzer.setHasOrigin(this.preserveOrigin);
        analyzer.setExactMatch(this.exactMatch);
        analyzer.setOriginCNoun(this.preserveCNoun);
        analyzer.setDecompound(this.decompound);
        analyzer.setWordSegment(this.wordSegment);

    }

    @Override
    public KoreanAnalyzer get() {
        return this.analyzer;
    }
}