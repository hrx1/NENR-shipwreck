package fer.nenr.fuzzycontrol.rule;

public interface ParameterChooser {
    int getParameter(int L, int D, int LK, int DK, int V, int S);

    ParameterChooser L = (L, D, LK, DK, V, S) -> L;
    ParameterChooser D = (L, D, LK, DK, V, S) -> D;
    ParameterChooser LK = (L, D, LK, DK, V, S) -> LK;
    ParameterChooser DK = (L, D, LK, DK, V, S) -> DK;
    ParameterChooser V = (L, D, LK, DK, V, S) -> V;
    ParameterChooser S = (L, D, LK, DK, V, S) -> S;

}
