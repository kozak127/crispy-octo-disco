package org.wesoly.michal.crispyoctodisco.problem1;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.CompareToBuilder;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class IndexedNumber implements Comparable<IndexedNumber> {

    private final int index;

    private final int value;

    @Override
    public int compareTo(IndexedNumber other) {
        return new CompareToBuilder().append(this.value, other.value).toComparison();
    }

    @Override
    public String toString() {
        return "(" + index + ":" + value + ")";
    }
}
