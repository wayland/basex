package org.basex.query.expr;

import org.basex.query.QueryException;
import org.basex.query.item.Item;
import org.basex.query.item.Value;

/**
 * GroupNode defines one valid partitioning setting.
 *
 * @author Workgroup DBIS, University of Konstanz 2005-10, ISC License
 * @author Michael Seiferle
 */
final class GroupNode {
  /** Cached hash code. */
  private final int hash;
  /** List of grouping values. */
  final Value[] vals;

  /**
   * Creates a group node.
   * @param vl grouping values
   */
  GroupNode(final Value[] vl) {
    vals = vl;
    int h = 0;
    for(final Value v : vals) h = (h << 5) - h + v.hashCode();
    hash = h;
  }

  /**
   * Checks the nodes for equality.
   * @param c second group node
   * @return result of check
   * @throws QueryException query exception
   */
  boolean eq(final GroupNode c) throws QueryException {
    if(vals.length != c.vals.length) return false;
    for(int i = 0; i < vals.length; ++i) {
      final boolean it = vals[i].item();
      if(it ^ c.vals[i].item() || it &&
          !((Item) vals[i]).equiv(null, (Item) c.vals[i])) return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    return hash;
  }
}
