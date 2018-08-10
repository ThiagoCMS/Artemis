package br.ufrpe.artemis.infra;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.ufrpe.artemis.avaliacao.negocio.AvaliacaoNegocio;
import br.ufrpe.artemis.pessoa.dominio.Pessoa;

/**
 * Slope One algorithm implementation
 */
public class SlopeOne {

    private static Map<Pessoa, Map<Pessoa, Double>> diff = new HashMap<>();
    private static Map<Pessoa, Map<Pessoa, Integer>> freq = new HashMap<>();
    private static Map<Pessoa, HashMap<Pessoa, Double>> inputData;
    private static Map<Pessoa, HashMap<Pessoa, Double>> outputData = new HashMap<>();

    public static void slopeOne() {
        AvaliacaoNegocio avaliacaoNegocio = new AvaliacaoNegocio();
        inputData = avaliacaoNegocio.retornarMapAvaliacoes();
        buildDifferencesMatrix(inputData);
        predict(inputData, avaliacaoNegocio.retornarPrestadorasAvaliadas());
    }

    /**
     * Based on the available data, calculate the relationships between the
     *items and number of
     *
     * @param data
     *            existing user data and their items' ratings
     */
    private static void buildDifferencesMatrix(Map<Pessoa, HashMap<Pessoa, Double>> data) {
        for (HashMap<Pessoa, Double> user : data.values()) {//cada valor de cada chave
            for (Entry<Pessoa, Double> e : user.entrySet()) {
                if (!diff.containsKey(e.getKey())) {
                    diff.put(e.getKey(), new HashMap<Pessoa, Double>());
                    freq.put(e.getKey(), new HashMap<Pessoa, Integer>());
                }
                for (Entry<Pessoa, Double> e2 : user.entrySet()) {
                    int oldCount = 0;
                    if (freq.get(e.getKey()).containsKey(e2.getKey())) {
                        oldCount = freq.get(e.getKey()).get(e2.getKey()).intValue();
                    }
                    double oldDiff = 0.0;
                    if (diff.get(e.getKey()).containsKey(e2.getKey())) {
                        oldDiff = diff.get(e.getKey()).get(e2.getKey()).doubleValue();
                    }
                    double observedDiff = e.getValue() - e2.getValue();
                    freq.get(e.getKey()).put(e2.getKey(), oldCount + 1);
                    diff.get(e.getKey()).put(e2.getKey(), oldDiff + observedDiff);
                }
            }
        }
        for (Pessoa j : diff.keySet()) {
            for (Pessoa i : diff.get(j).keySet()) {
                double oldValue = diff.get(j).get(i).doubleValue();
                int count = freq.get(j).get(i).intValue();
                diff.get(j).put(i, oldValue / count);
            }
        }
    }

    /**
     * Based on existing data predict all missing ratings. If prediction is not
     * possible, the value will be equal to -1
     *
     * @param data
     *            existing user data and their items' ratings
     */
    private static void predict(Map<Pessoa, HashMap<Pessoa, Double>> data, List<Pessoa> listPrestadoras) {
        HashMap<Pessoa, Double> uPred = new HashMap<Pessoa, Double>();
        HashMap<Pessoa, Integer> uFreq = new HashMap<Pessoa, Integer>();
        for (Pessoa j : diff.keySet()) {
            uFreq.put(j, 0);
            uPred.put(j, 0.0);
        }
        for (Entry<Pessoa, HashMap<Pessoa, Double>> e : data.entrySet()) {
            for (Pessoa j : e.getValue().keySet()) {
                for (Pessoa k : diff.keySet()) {
                    try {
                        double predictedValue = diff.get(k).get(j).doubleValue() + e.getValue().get(j).doubleValue();
                        double finalValue = predictedValue * freq.get(k).get(j).intValue();
                        uPred.put(k, uPred.get(k) + finalValue);
                        uFreq.put(k, uFreq.get(k) + freq.get(k).get(j).intValue());
                    } catch (NullPointerException e1) {
                    }
                }
            }
            HashMap<Pessoa, Double> clean = new HashMap<Pessoa, Double>();
            for (Pessoa j : uPred.keySet()) {
                Boolean v = clean.containsKey(j);
                if (uFreq.get(j) > 0 && !v) {
                    clean.put(j, uPred.get(j).doubleValue() / uFreq.get(j).intValue());
                }
            }
            for (Pessoa j : listPrestadoras) {
                if (e.getValue().containsKey(j)) {
                    clean.put(j, e.getValue().get(j));
                }
            }
            outputData.put(e.getKey(), clean);
        }
    }

    public static Map<Pessoa, HashMap<Pessoa, Double>> getOutputData() {
        return outputData;
    }
}